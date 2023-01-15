create table if not exists news (
    id bigserial not null,
    description varchar(255),
    primary key (id)
);
create table if not exists  source (
    id bigserial not null,
    source_name varchar(255),
    primary key (id)
);
create table if not exists  topic (
    id bigserial not null,
    topic_name varchar(255),
    primary key (id)
);
create table if not exists  source_and_topic (
    source_id bigint,
    topic_id bigint not null,
    primary key (topic_id)
);
create table if not exists  topic (
    id bigserial not null,
    topic_name varchar(255),
    primary key (id)
);
create table if not exists  topic_and_news (
    topic_id bigint,
    news_id bigint not null,
    primary key (news_id)
);

alter table if exists source_and_topic
    add constraint FKabcdh5kq75b69elg1tpux851b
    foreign key (source_id)
    references source;

alter table if exists source_and_topic
    add constraint FK2c9djyu019i0allhhyj770rdc
    foreign key (topic_id)
    references topic;

alter table if exists topic_and_news
    add constraint FKpsg6drl4qwxwtc36evp3y1ykf
    foreign key (topic_id)
    references topic;

alter table if exists topic_and_news
    add constraint FK7rwjky6hsji0tjuj8kneqsdrh
    foreign key (news_id)
    references news;