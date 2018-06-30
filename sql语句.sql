/**定位城市后选择所在区*/
CREATE TABLE `t_country` (
  `cityName` varchar(64) NOT NULL,
  `countryId` int(8) NOT NULL,
  `countryName` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**商品信息*/
CREATE TABLE `t_goods` (
  `goodsPicture` varchar(255) DEFAULT NULL,
  `goodsNum` bigint(255) NOT NULL,
  `goodsName` varchar(128) DEFAULT NULL,
  `goodsPrice` double(128,1) DEFAULT NULL,
  `goodsSurplus` int(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**购物车里的商品信息*/
CREATE TABLE `t_goodscar` (
  `userTel` varchar(128) NOT NULL,
  `goodsPicture` varchar(255) DEFAULT NULL,
  `goodsNum` bigint(255) DEFAULT NULL,
  `goodsName` varchar(128) DEFAULT NULL,
  `goodsPrice` double(128,1) DEFAULT NULL,
  `goodsBuyNum` int(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**订单信息*/
CREATE TABLE `t_order` (
  `orderNum` bigint(255) NOT NULL,
  `orderStore` varchar(128) DEFAULT NULL,
  `userTel` varchar(255) DEFAULT NULL,
  `userAddress` varchar(255) DEFAULT NULL,
  `goodsCarAllGoods` varchar(255) DEFAULT NULL,
  `totalGoodsPrice` double(128,1) DEFAULT NULL,
  `orderStatus` int(2) DEFAULT NULL,
  `downOrderTime` varchar(126) DEFAULT NULL,
  PRIMARY KEY (`orderNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**所在区选择后选择所在的商铺*/
CREATE TABLE `t_store` (
  `countryId` int(8) NOT NULL,
  `storeId` int(8) NOT NULL,
  `storeName` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**用户信息*/
CREATE TABLE `t_user` (
  `userName` varchar(64) DEFAULT NULL,
  `userSex` int(2) DEFAULT NULL,
  `userPassword` varchar(128) NOT NULL,
  `userTel` varchar(128) NOT NULL,
  `userPayable` double(128,0) DEFAULT NULL,
  `userAddress` varchar(256) DEFAULT NULL,
  `userPower` int(2) DEFAULT NULL,
  `userPayPassword` varchar(128) NOT NULL,
  `uuidString` varchar(128) NOT NULL,
  `power` varchar(16) NOT NULL,
  `uuidPasswordString` varchar(128) NOT NULL,
  PRIMARY KEY (`userTel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
