drop table if exists books cascade;

create table books (
  isbn_13 varchar (13) primary key,
  title varchar (100),
  author varchar (80),
  publish_date date,
  price decimal (6,2),
  content bytea
);

create table book_tags (
	isbn_13 varchar (13), foreign key (isbn_13) references books (isbn_13) on delete cascade,
	name_tag varchar
);

insert into books values (
  '1111111111111',          	-- id
  'The Adventures of Steve',    -- title
  'Russell Barron', 			-- author
  current_date,    				-- publishDate
  123.50,   					-- price
  null							-- blob
);

insert into book_tags VALUES (
'1111111111111',
'adventure');

select* from BOOK_TAGS;