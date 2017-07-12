/**
 * 1.创建数据库
 */
 drop database if exists Visitor;
 create database Visitor;

/**
 * 2.创建表
 */ 
  use Visitor;
  create table users
  (
    ID int(4) not null primary key auto_increment,
    UserName varchar(100),
    Pwd varchar(50)  
  );
 
  create table visitors
  (
    ID int(4) not null primary key auto_increment,
    UserID int(4),
    VisitTime datetime,
    LeftTime datetime,
    ip varchar(50),
    comefrom varchar(100)
  );

  create table history
  (
    ID int(4) not null primary key auto_increment,
    VisitID	int(4),
    VisitTime datetime,
    Url varchar(200)
  );
/**
 * 为users表添加3个初始用户
 */
	insert into users (username,pwd) values ("小A","123456");
	insert into users (username,pwd) values ("小B","abcdef");
	insert into users (username,pwd) values ("小C","123abc");
 