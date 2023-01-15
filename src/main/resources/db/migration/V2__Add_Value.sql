insert into source (source_name) values ('irbis.plus');
insert into source (source_name) values('praktika.irbis.plus');

insert into topic (topic_name) values ('О нас'), ('Помощь юр. лицам'),
                                      ('Помощь физ. лицам'), ('Обновления сервиса');

insert into news (description) values ('Обновления законодательства в 2022 году.'),
                                      ('Обновления законодательства в 2023 г.'),
                                      ('Рассказываем о том, как обезопасить себя от мошенников'),
                                      ('Рассказываем о том, как отдыхают наши работники'),
                                      ('Знакомим с нашими клиентами. Часть 1'),
                                      ('Знакомим с нашими клиентами. Часть 2'),
                                      ('Знакомство с сервисом'),
                                      ('Нововведения во вкладке "Суды"');

insert into source_and_topic (source_id, topic_id) values ((select id from source s where s.source_name='irbis.plus'),
                                                           (select id from topic t where t.topic_name='О нас')),

                                                          ((select id from source s where s.source_name='irbis.plus'),
                                                           (select id from topic t where t.topic_name='Помощь физ. лицам')),

                                                          ((select id from source s where s.source_name='irbis.plus'),
                                                           (select id from topic t where t.topic_name='Помощь юр. лицам')),

                                                          ((select id from source s where s.source_name='praktika.irbis.plus'),
                                                           (select id from topic t where t.topic_name='Обновления сервиса'));

insert into topic_and_news (topic_id, news_id) values ((select id from topic t where t.topic_name='О нас'),
                                                       (select id from news n where n.description='Рассказываем о том, как отдыхают наши работники')),

                                                      ((select id from topic t where t.topic_name='О нас'),
                                                       (select id from news n where n.description='Знакомим с нашими клиентами. Часть 1')),

                                                      ((select id from topic t where t.topic_name='О нас'),
                                                       (select id from news n where n.description='Знакомим с нашими клиентами. Часть 2')),

                                                      ((select id from topic t where t.topic_name='Помощь физ. лицам'),
                                                       (select id from news n where n.description='Рассказываем о том, как обезопасить себя от мошенников')),

                                                      ((select id from topic t where t.topic_name='Помощь юр. лицам'),
                                                       (select id from news n where n.description='Обновления законодательства в 2023 г.')),

                                                      ((select id from topic t where t.topic_name='Помощь юр. лицам'),
                                                       (select id from news n where n.description='Обновления законодательства в 2022 году.')),

                                                      ((select id from topic t where t.topic_name='Обновления сервиса'),
                                                       (select id from news n where n.description='Знакомство с сервисом')),

                                                      ((select id from topic t where t.topic_name='Обновления сервиса'),
                                                       (select id from news n where n.description='Нововведения во вкладке "Суды"'));






