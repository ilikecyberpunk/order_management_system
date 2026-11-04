create DATABASE if NOT EXISTS burger_shop;
use burger_shop;
create table burgers(
    id int primary key auto_increment,
    name varchar(50) not null,
    price int not null,
    stock int not null
); ENGINE=innoDB; -- innoDB -> 트랜잭션 지원(commit, rollback) -> MySQL기본 스토리지 엔진인데 그냥 명시적으로 함 생략해도 됨

create table orders(
    id int PRIMARY key AUTO_INCREMENT,
    burger_id int not null,
    quantity int not null,
    total_price int not null,

    foreign key(burger_id) references burgers(id)
)