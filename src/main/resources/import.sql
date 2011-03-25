insert into company (id, name, nip, regon) values (1, 'LG', '4631246319', '634124827')
insert into company (id, name, nip, regon) values (2, 'Microsoft Corp.', '6324613294', '732647327')

insert into employee (id, firstname, surname, pesel, nip, yob, company_id) values (1, 'Michal', 'Gosc', '89060612345', '6423463282', 1989, 1)
insert into employee (id, firstname, surname, pesel, nip, yob, company_id) values (2, 'Zosia', 'Sculi', '89010233344', '5324277432', 1989, 1)
insert into employee (id, firstname, surname, pesel, nip, yob, company_id) values (3, 'Fox', 'Mulder', '82021810012', '6473472663', 1982, 2)

insert into role (id, name) values (1, 'admin')

insert into account (login, password, company_id, role_id) values ('admin', 'pass', 2, 1)