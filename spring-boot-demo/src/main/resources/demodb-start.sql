DROP DATABASE IF EXISTS demodb;
CREATE DATABASE IF NOT EXISTS demodb;
USE demodb;

CREATE TABLE employee (
	name		VARCHAR(20) ,
    id			VARCHAR(20) PRIMARY KEY,
	gender		ENUM('male', 'female') ,
	cellphone	VARCHAR(20) ,
    address		VARCHAR(20) ,
    age			SMALLINT ,
    created		TIMESTAMP DEFAULT 0,	
	updated		TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

SET SQL_SAFE_UPDATES=0;

INSERT employee VALUES("Chuck", "9487", "male", "0987487987", "Ilan", 35, NULL, NULL);
INSERT employee VALUES("Gary", "9488", "male", "0988408408", "Changhua", 26, NULL, NULL);
INSERT employee VALUES("Ada", "9489", "female", "0978478478", "Taipei", 21, NULL, NULL);
INSERT employee VALUES("Gary", "9490", "male", "0987889126", "Taipei", 23, NULL, NULL);
INSERT employee VALUES("Adam", "9491", "male", "0988888168", "Taipei", 39, NULL, NULL);
INSERT employee VALUES("Mary", "9492", "female", "0938438438", "Taipei", 16, NULL, NULL);
INSERT employee VALUES("Chris", "9493", "male", "0986123456", "Taipei", 27, NULL, NULL);
INSERT employee VALUES("Ben", "9494", "male", "0935879512", "Beijing", 54, NULL, NULL);
INSERT employee VALUES("Jesse", "9495", "male", "0955174859", "Keelung", 29, NULL, NULL);
INSERT employee VALUES("Raymond", "9496", "male", "0924020404", "Taipei", 34, NULL, NULL);
INSERT employee VALUES("Kerena", "9497", "female", "0907007007", "LA", 41, NULL, NULL);
INSERT employee VALUES("Mike", "9498", "male", "0977747747", "Taipei", 44, NULL, NULL);
INSERT employee VALUES("Hogan", "9499", "male", "0952088088", "Taipei", 35, NULL, NULL);
INSERT employee VALUES("Annie", "9500", "female", "0910012300", "Tainan", 33, NULL, NULL);
INSERT employee VALUES("Sam", "9501", "male", "0910075500", "Taoyuan", 42, NULL, NULL);