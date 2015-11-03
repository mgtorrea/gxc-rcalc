insert into companies(id,name,url,description) values(1,'aeros','www.aeros.com','some desc for aeros');
insert into companies(id,name,url,description) values(2,'bombardier','www.bomb.com.mx','some desc for bombardier');
insert into companies(id,name,url,description) values(3,'medicare','www.medicare.com','some desc for medicare');
insert into companies(id,name,url,description) values(4,'bmer','www.bmer.mx','some desc for bancomer');

insert into risk_index(id,created_at,risk,company_id) values(1,now(),4.3,1);
insert into risk_index(id,created_at,risk,company_id) values(2,now(),1.3,2);
insert into risk_index(id,created_at,risk,company_id) values(3,now(),2.5,3);
insert into risk_index(id,created_at,risk,company_id) values(4,now(),0.8,4);
insert into risk_index(id,created_at,risk,company_id) values(5,now(),3.9,4);	