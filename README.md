# portfolio-01

-- community_site.tb_member definition
```
CREATE TABLE `tb_member` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `userId` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nickName` varchar(50) NOT NULL,
  `grade` int(1) NOT NULL DEFAULT 0,
  `regDt` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `failCnt` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idx`),
  UNIQUE KEY `tb_member_un` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='사용자 정보 테이블';
```
