# portfolio-01

Summernote는 image업로드를 base64방식으로 진행 => 용량이 금방 커진다...
Tomcat server.xml에 maxPostSize의 default값은 2MB.
2MB이상 post전송시 에러발생으로 maxPostSize값을 -1로 설정한다.

-- community_site.tb_member definition
```
CREATE TABLE `tb_member` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `userId` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nickName` varchar(50) NOT NULL,
  `grade` int(1) NOT NULL DEFAULT 0,
  `regDt` timestamp NOT NULL DEFAULT current_timestamp(),
  `failCnt` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idx`),
  UNIQUE KEY `tb_member_un` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='사용자 정보 테이블';
```

-- community_site.tb_category definition
```
CREATE TABLE `tb_category` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `categoryNm` varchar(100) CHARACTER SET utf8mb4 NOT NULL,
  `categoryNo` varchar(100) CHARACTER SET utf8mb4 NOT NULL,
  `depth` int(5) NOT NULL DEFAULT 0,
  `url` varchar(500) CHARACTER SET utf8mb4 DEFAULT NULL,
  `parent` bigint(20) NOT NULL DEFAULT 0,
  `order` int(11) NOT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='카테고리 테이블';
```
-- community_site.tb_board_tech definition
```
CREATE TABLE `tb_board_tech` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(500) NOT NULL,
  `content` text NOT NULL,
  `regDate` timestamp NOT NULL DEFAULT current_timestamp(),
  `writer` varchar(100) NOT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tech 게시판 테이블';
```

