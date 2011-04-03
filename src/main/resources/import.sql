insert into company (id, name, nip, regon) values (1, 'LG', '4631246319', '634124827')
insert into company (id, name, nip, regon) values (2, 'Microsoft Corp.', '6324613294', '732647327')

insert into employee (id, firstname, surname, pesel, nip, yob, company_id) values (1, 'Michal', 'Gość', '89060612345', '642-346-32-82', 1989, 1)
insert into employee (id, firstname, surname, pesel, nip, yob, company_id) values (2, 'Zosia', 'Sculi', '89010233344', '532-427-74-32', 1989, 1)
insert into employee (id, firstname, surname, pesel, nip, yob, company_id) values (3, 'Fox', 'Mulder', '82021810012', '647-347-26-63', 1982, 2)
insert into employee (id, firstname, surname, pesel, nip, yob, company_id) values (4, 'Adam', 'Ark', '87060612345', '642-346-32-82', 1987, 2)
insert into employee (id, firstname, surname, pesel, nip, yob, company_id) values (5, 'Mateusz', 'Glom', '90010233344', '532-427-74-32', 1990, 1)
insert into employee (id, firstname, surname, pesel, nip, yob, company_id) values (6, 'Albert', 'Batman', '82021810012', '647-347-26-63', 1982, 2)
insert into employee (id, firstname, surname, pesel, nip, yob, company_id) values (7, 'Lucjan', 'Prawy', '84060612345', '642-346-32-82', 1984, 2)
insert into employee (id, firstname, surname, pesel, nip, yob, company_id) values (8, 'Scala', 'Johansson', '80010233344', '532-427-74-32', 1980, 1)
insert into employee (id, firstname, surname, pesel, nip, yob, company_id) values (9, 'Ada', 'Wendows', '76021810012', '647-347-26-63', 1976, 2)

insert into role (id, name) values (1, 'admin')
insert into role (id, name) values (2, 'company')
insert into role (id, name) values (3, 'employee')

insert into account (login, password, company_id, role_id) values ('admin', '25e4ee4e9229397b6b17776bfceaf8e7', 2, 1)
insert into account (login, password, company_id, role_id) values ('user', '63e780c3f321d13109c71bf81805476e', 1, 2)

insert into rank (id, name, hourSalary, company_id) values (1, 'sprzedawca', 6.0 , 2)