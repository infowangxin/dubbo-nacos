package com.xxl.job.core.handler.impl;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.biz.model.TriggerParam;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.util.StringUtils;

import java.util.Map;


public class DubboJobHandler extends IJobHandler {
 
	/** dubbo服务的方法名 */
	private String serviceMethod;
	/** dubbo服务的方法参数类型 */
	private String[] types = new String[]{};
	/** dubbo服务的方法参数值 */
	private Object[] values = new Object[]{};
	
	
	// dubbo泛化调用
	private ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
	
	
	/**
	 * java.lang.String&java.lang.Integer&java.util.Map|hello world&111&{"code":"ttp"}
	 * @param executorParams
	 */
	private void buildParameter(String executorParams){
		String[] typeValues = executorParams.split("\\|");
		if(typeValues.length != 2){
			XxlJobLogger.log("dubbo server " + reference.getInterface() + "." + this.serviceMethod + " trans param error！");
			return;
		}
		if(typeValues[0].split("\\&").length != typeValues[1].split("\\&").length){
			XxlJobLogger.log("dubbo server " + reference.getInterface() + "." + this.serviceMethod + " trans param error！");
			return;
		}
		types = typeValues[0].split("\\&");
		
		values = new Object[types.length];
		String[] StringValues = typeValues[1].split("\\&");
		for(int i = 0; i < types.length; i++){
			switch (types[i]) {
			case "java.lang.String":
				values[i] = StringValues[i];
				break;
			case "java.lang.Integer":
				values[i] = Integer.parseInt(StringValues[i]);
				break;
			default:
				values[i] = JSON.parseObject(StringValues[i], Map.class);
				break;
			}
			
		}
	}
	
	public DubboJobHandler(String address, TriggerParam triggerParam){
		ApplicationConfig application = new ApplicationConfig();
		application.setName("dubbo-nacos-xxl-job-admin");
		reference.setApplication(application);
		
		RegistryConfig registryConfig = new RegistryConfig();
		registryConfig.setAddress(address);
	    // registryConfig.setProtocol("zookeeper");
	    registryConfig.setProtocol("dubbo");
	    reference.setRegistry(registryConfig);
	    
	    // 声明为泛化接口
	 	reference.setGeneric(true);
	 	// 弱类型接口名
	 	reference.setInterface(triggerParam.getExecutorHandler());
	 	reference.setVersion(triggerParam.getDubboVersion());
	 	// s=》ms
	 	reference.setTimeout(triggerParam.getExecutorTimeout() * 1000);
	 	reference.setRetries(0);
	 	
	 	this.serviceMethod = triggerParam.getDubboMethod();
	 	
	 	if(!StringUtils.isEmpty(triggerParam.getExecutorParams())){
	 		buildParameter(triggerParam.getExecutorParams());
	 	}
	}

	@Override
	public ReturnT<String> execute(String param) throws Exception {
	    
		if(!StringUtils.isEmpty(param)){
	 		buildParameter(param);
	 	}
		// 用com.alibaba.dubbo.rpc.service.GenericService可以替代所有接口引用
		GenericService genericService = ReferenceConfigCache.getCache().get(reference);
		if (genericService == null) {
			ReferenceConfigCache.getCache().destroy(reference);
			XxlJobLogger.log("dubbo server " + reference.getInterface() + "." + this.serviceMethod + " is not founded！");
			return new ReturnT<String>(IJobHandler.FAIL.getCode(), "dubbo server " +  this.serviceMethod + " is not founded！" );
		}
		
		try {
			Object obj = genericService.$invoke(this.serviceMethod, types, values);
			ReturnT<String> result = new ReturnT<>(200,"success");
			result.setContent(obj == null ? "":JSON.toJSONString(obj));
			return result;
		} catch (Exception e) {
			ReturnT<String> result = new ReturnT<String>(500,"fail");
			result.setContent(JSON.toJSONString(e));
			return result;
		}
		
		
	}

}
