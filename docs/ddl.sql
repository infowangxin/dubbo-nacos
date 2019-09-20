CREATE TABLE `dn_permission` (
   `id` bigint(19)  NOT NULL  COMMENT 'Pk',
   `name` varchar(20)  NOT NULL  COMMENT 'permission name',
   `fa` varchar(20)  COMMENT 'permission code',
   `url` varchar(100)  COMMENT 'permission url',
   `lev` int(3)  COMMENT 'permission level',
   `sort` int(8)  COMMENT 'permission sort',
   `parent_id` bigint(19)  COMMENT 'parent permission id',
   `site_code` varchar(20)  COMMENT 'site type',
   `remark` varchar(255)  COMMENT 'permission remark',
   PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Permission';

CREATE TABLE `dn_role` (
   `id` bigint(19)  NOT NULL  COMMENT 'Pk',
   `role_name` varchar(20)  NOT NULL  COMMENT 'role name',
   `role_code` varchar(25)  NOT NULL  COMMENT 'role code',
   `parent_id` bigint(19)  COMMENT 'parent role id',
   `role_type` varchar(8)  COMMENT 'role type',
   `remark` varchar(255)  COMMENT 'role remark',
   PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Role';

CREATE TABLE `dn_role_permission` (
   `id` bigint(19)  NOT NULL  COMMENT 'Pk',
   `role_id` bigint(19)  NOT NULL  COMMENT 'role id',
   `permission_id` bigint(19)  COMMENT 'permission id',
   `site_code` varchar(20)  NOT NULL  COMMENT 'site type',
   PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Role Permission';

CREATE TABLE `dn_user` (
   `id` bigint(19)  NOT NULL  COMMENT 'Pk',
   `account` varchar(20)  NOT NULL  COMMENT 'user login account',
   `password` varchar(200)  NOT NULL  COMMENT 'user login password',
   `real_name` varchar(20)  NOT NULL  COMMENT 'user real name',
   `email` varchar(50)  COMMENT 'user email',
   `mobile` int(15)  COMMENT 'user mobile',
   `salt` varchar(100)  COMMENT 'salt code ',
   PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='User';

CREATE TABLE `dn_user_role` (
   `id` bigint(19)  NOT NULL  COMMENT 'Pk',
   `user_id` bigint(19)  NOT NULL  COMMENT 'user id',
   `role_id` bigint(19)  COMMENT 'role id',
   PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='User Role';


insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000001,	'GENERAL',	null,	null,	0,	1,	null,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000002,	'Home',	'fa fa-home',	null,	1,	2,	1908032303000000001,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000003,	'Dashboard',	null,	'production/index.html',	2,	3,	1908032303000000002,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000004,	'Dashboard2',	null,	'production/index2.html',	2,	4,	1908032303000000002,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000005,	'Dashboard3',	null,	'production/index3.html',	2,	5,	1908032303000000002,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000006,	'Forms',	'fa fa-edit',	null,	1,	6,	1908032303000000001,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000007,	'General Form',	null,	'production/form.html',	2,	7,	1908032303000000006,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000008,	'Advanced Acomponents',	null,	'production/form_advanced.html',	2,	8,	1908032303000000006,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000009,	'Form Validation',	null,	'production/form_validation.html',	2,	9,	1908032303000000006,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000010,	'Form Wizard',	null,	'production/form_wizards.html',	2,	10,	1908032303000000006,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000011,	'Form Upload',	null,	'production/form_upload.html',	2,	11,	1908032303000000006,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000012,	'Form Buttons',	null,	'production/form_buttons.html',	2,	12,	1908032303000000006,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000013,	'UI Elements',	'fa fa-desktop',	null,	1,	13,	1908032303000000001,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000014,	'General Elements',	null,	'production/general_elements.html',	2,	14,	1908032303000000013,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000015,	'Media Gallery',	null,	'production/media_gallery.html',	2,	15,	1908032303000000013,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000016,	'Typography',	null,	'production/typography.html',	2,	16,	1908032303000000013,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000017,	'Lcons',	null,	'production/icons.html',	2,	17,	1908032303000000013,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000018,	'Glyphicons',	null,	'production/glyphicons.html',	2,	18,	1908032303000000013,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000019,	'Widegets',	null,	'production/widgets.html',	2,	19,	1908032303000000013,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000020,	'Invoice',	null,	'production/invoice.html',	2,	20,	1908032303000000013,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000021,	'Inbox',	null,	'production/inbox.html',	2,	21,	1908032303000000013,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000022,	'Calendar',	null,	'production/calendar.html',	2,	22,	1908032303000000013,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000023,	'Tables',	'fa fa-table',	null,	1,	23,	1908032303000000001,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000024,	'Tables',	null,	'production/tables.html',	2,	24,	1908032303000000023,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000025,	'Tables Dynamic',	null,	'production/tables_dynamic.html',	2,	25,	1908032303000000023,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000026,	'Data Presentation',	'fa fa-bar-chart-o',	null,	1,	26,	1908032303000000001,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000027,	'Chart JS',	null,	'production/chartjs.html',	2,	27,	1908032303000000026,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000028,	'Chart JS2',	null,	'production/chartjs2.html',	2,	28,	1908032303000000026,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000029,	'Moris JS',	null,	'production/morisjs.html',	2,	29,	1908032303000000026,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000030,	'Echarts',	null,	'production/echarts.html',	2,	30,	1908032303000000026,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000031,	'Others Charts',	null,	'production/other_charts.html',	2,	31,	1908032303000000026,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000032,	'Layouts',	'fa fa-clone',	null,	1,	32,	1908032303000000001,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000033,	'Fixed Sidebar',	null,	'production/fixed_sidebar.html',	2,	33,	1908032303000000032,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000034,	'Fixed Footer',	null,	'production/fixed_footer.html',	2,	34,	1908032303000000032,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000035,	'LIVE  ON',	null,	null,	0,	35,	null,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000036,	'Additional Pages',	'fa fa-bug',	null,	1,	36,	1908032303000000035,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000037,	'E-commerce',	null,	'production/e_commerce.html',	2,	37,	1908032303000000036,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000038,	'Projects',	null,	'production/projects.html',	2,	38,	1908032303000000036,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000039,	'Project Detail',	null,	'production/project_detail.html',	2,	39,	1908032303000000036,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000040,	'Contacts',	null,	'production/contacts.html',	2,	40,	1908032303000000036,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000041,	'Profile',	null,	'production/profile.html',	2,	41,	1908032303000000036,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000042,	'Extras',	'fa fa-windows',	null,	1,	42,	1908032303000000035,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000043,	'403  Error',	null,	'production/page_403.html',	2,	43,	1908032303000000042,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000044,	'404 Error',	null,	'production/page_404.html',	2,	44,	1908032303000000042,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000045,	'500 Error',	null,	'production/page_500.html',	2,	45,	1908032303000000042,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000046,	'Plain Page',	null,	'production/plain_page.html',	2,	46,	1908032303000000042,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000047,	'Login Page',	null,	'production/login.html',	2,	47,	1908032303000000042,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000048,	'Pricing Tables',	null,	'production/pricing_tables.html',	2,	48,	1908032303000000042,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000049,	'Multilevel Menu',	'fa fa-sitemap',	null,	1,	49,	1908032303000000035,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000050,	'Level  One',	null,	'pricing_tables.html#level1_1',	2,	50,	1908032303000000049,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000051,	'Level One',	null,	null,	2,	51,	1908032303000000049,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000052,	'Level Two',	null,	'production/level2.html',	3,	52,	1908032303000000051,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000053,	'Level Two',	null,	'production/level2.html#level2_1',	3,	53,	1908032303000000051,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000054,	'Level Two',	null,	'production/level2.html#level2_2',	3,	54,	1908032303000000051,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000055,	'Level One',	null,	'production/level2.html#level1_2',	3,	55,	1908032303000000051,	'dubbo-nacos-consumer',	null	);
insert into  dn_permission(id,`name`,fa,url,lev,sort,parent_id,site_code,remark) values(	1908032303000000056,	'Landing Page',	'fa fa-laptop',	null,	1,	56,	1908032303000000035,	'dubbo-nacos-consumer',	null	);

insert into dn_role(id,role_name,role_code,parent_id,role_type,remark) values(	1908040851000000001,	'System Admin Role',	'admin',	null,	'admin',	null	);
insert into dn_role(id,role_name,role_code,parent_id,role_type,remark) values(	1908040851000000002,	'System Customer Role',	'customer',	null,	null,	null	);

insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000001,	1908040851000000001,	1908032303000000001,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000002,	1908040851000000001,	1908032303000000002,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000003,	1908040851000000001,	1908032303000000003,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000004,	1908040851000000001,	1908032303000000004,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000005,	1908040851000000001,	1908032303000000005,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000006,	1908040851000000001,	1908032303000000006,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000007,	1908040851000000001,	1908032303000000007,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000008,	1908040851000000001,	1908032303000000008,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000009,	1908040851000000001,	1908032303000000009,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000010,	1908040851000000001,	1908032303000000010,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000011,	1908040851000000001,	1908032303000000011,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000012,	1908040851000000001,	1908032303000000012,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000013,	1908040851000000001,	1908032303000000013,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000014,	1908040851000000001,	1908032303000000014,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000015,	1908040851000000001,	1908032303000000015,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000016,	1908040851000000001,	1908032303000000016,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000017,	1908040851000000001,	1908032303000000017,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000018,	1908040851000000001,	1908032303000000018,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000019,	1908040851000000001,	1908032303000000019,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000020,	1908040851000000001,	1908032303000000020,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000021,	1908040851000000001,	1908032303000000021,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000022,	1908040851000000001,	1908032303000000022,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000023,	1908040851000000001,	1908032303000000023,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000024,	1908040851000000001,	1908032303000000024,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000025,	1908040851000000001,	1908032303000000025,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000026,	1908040851000000001,	1908032303000000026,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000027,	1908040851000000001,	1908032303000000027,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000028,	1908040851000000001,	1908032303000000028,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000029,	1908040851000000001,	1908032303000000029,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000030,	1908040851000000001,	1908032303000000030,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000031,	1908040851000000001,	1908032303000000031,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000032,	1908040851000000001,	1908032303000000032,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000033,	1908040851000000001,	1908032303000000033,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000034,	1908040851000000001,	1908032303000000034,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000035,	1908040851000000001,	1908032303000000035,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000036,	1908040851000000001,	1908032303000000036,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000037,	1908040851000000001,	1908032303000000037,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000038,	1908040851000000001,	1908032303000000038,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000039,	1908040851000000001,	1908032303000000039,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000040,	1908040851000000001,	1908032303000000040,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000041,	1908040851000000001,	1908032303000000041,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000042,	1908040851000000001,	1908032303000000042,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000043,	1908040851000000001,	1908032303000000043,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000044,	1908040851000000001,	1908032303000000044,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000045,	1908040851000000001,	1908032303000000045,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000046,	1908040851000000001,	1908032303000000046,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000047,	1908040851000000001,	1908032303000000047,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000048,	1908040851000000001,	1908032303000000048,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000049,	1908040851000000001,	1908032303000000049,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000050,	1908040851000000001,	1908032303000000050,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000051,	1908040851000000001,	1908032303000000051,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000052,	1908040851000000001,	1908032303000000052,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000053,	1908040851000000001,	1908032303000000053,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000054,	1908040851000000001,	1908032303000000054,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000055,	1908040851000000001,	1908032303000000055,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000056,	1908040851000000001,	1908032303000000056,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000057,	1908040851000000002,	1908032303000000001,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000058,	1908040851000000002,	1908032303000000002,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000059,	1908040851000000002,	1908032303000000003,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000060,	1908040851000000002,	1908032303000000004,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000061,	1908040851000000002,	1908032303000000005,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000062,	1908040851000000002,	1908032303000000006,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000063,	1908040851000000002,	1908032303000000007,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000064,	1908040851000000002,	1908032303000000008,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000065,	1908040851000000002,	1908032303000000009,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000066,	1908040851000000002,	1908032303000000010,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000067,	1908040851000000002,	1908032303000000011,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000068,	1908040851000000002,	1908032303000000012,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000069,	1908040851000000002,	1908032303000000013,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000070,	1908040851000000002,	1908032303000000014,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000071,	1908040851000000002,	1908032303000000015,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000072,	1908040851000000002,	1908032303000000016,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000073,	1908040851000000002,	1908032303000000017,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000074,	1908040851000000002,	1908032303000000018,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000075,	1908040851000000002,	1908032303000000019,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000076,	1908040851000000002,	1908032303000000020,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000077,	1908040851000000002,	1908032303000000021,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000078,	1908040851000000002,	1908032303000000022,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000079,	1908040851000000002,	1908032303000000023,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000080,	1908040851000000002,	1908032303000000024,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000081,	1908040851000000002,	1908032303000000025,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000082,	1908040851000000002,	1908032303000000026,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000083,	1908040851000000002,	1908032303000000027,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000084,	1908040851000000002,	1908032303000000028,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000085,	1908040851000000002,	1908032303000000029,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000086,	1908040851000000002,	1908032303000000030,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000087,	1908040851000000002,	1908032303000000031,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000088,	1908040851000000002,	1908032303000000032,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000089,	1908040851000000002,	1908032303000000033,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000090,	1908040851000000002,	1908032303000000034,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000091,	1908040851000000002,	1908032303000000035,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000092,	1908040851000000002,	1908032303000000036,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000093,	1908040851000000002,	1908032303000000037,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000094,	1908040851000000002,	1908032303000000038,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000095,	1908040851000000002,	1908032303000000039,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000096,	1908040851000000002,	1908032303000000040,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000097,	1908040851000000002,	1908032303000000041,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000098,	1908040851000000002,	1908032303000000042,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000099,	1908040851000000002,	1908032303000000043,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000100,	1908040851000000002,	1908032303000000044,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000101,	1908040851000000002,	1908032303000000045,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000102,	1908040851000000002,	1908032303000000046,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000103,	1908040851000000002,	1908032303000000047,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000104,	1908040851000000002,	1908032303000000048,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000105,	1908040851000000002,	1908032303000000049,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000106,	1908040851000000002,	1908032303000000050,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000107,	1908040851000000002,	1908032303000000051,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000108,	1908040851000000002,	1908032303000000052,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000109,	1908040851000000002,	1908032303000000053,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000110,	1908040851000000002,	1908032303000000054,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000111,	1908040851000000002,	1908032303000000055,	'dubbo-nacos-consumer'	);
insert into dn_role_permission(id,role_id,permission_id,site_code) values(	1908040853000000112,	1908040851000000002,	1908032303000000056,	'dubbo-nacos-consumer'	);

