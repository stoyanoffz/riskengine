DO $$
BEGIN
FOR user_counter IN 1..100 LOOP
                -- More than 10 transactions for less than a minute
                IF user_counter > 1 AND user_counter < 10 THEN
                    FOR record_counter IN 1..20 LOOP
                            INSERT INTO fraudulent_transactions
                            (id, identifier, user_id, amount, timestamp, country, lat_coord, long_coord)
                            VALUES (nextval('fraudulent_transactions_seq'),
                                    md5(random()::text),
                                    user_counter::text,
                                    random() * 1000,
                                    now() - interval '1 minute',
                                    'BG',
                                    random() * 180 - 90,
                                    random() * 360 - 180);
END LOOP;

                    -- Transactions where the location is over 300 km apart
                ELSIF user_counter > 10 AND user_counter < 20 THEN
                    INSERT INTO fraudulent_transactions
                    (id, identifier, user_id, amount, timestamp, country, lat_coord, long_coord)
                    VALUES (nextval('fraudulent_transactions_seq'),
                            md5(random()::text),
                            user_counter::text,
                            random() * 1000,
                            now() - interval '15 minutes',
                            'BG',
                            42.6977,
                            23.3219);

INSERT INTO fraudulent_transactions
(id, identifier, user_id, amount, timestamp, country, lat_coord, long_coord)
VALUES (nextval('fraudulent_transactions_seq'),
        md5(random()::text),
        user_counter::text,
        random() * 1000,
        now() - interval '5 minutes',
        'FR',
        48.8566,
        2.3522);

-- Transactions in different countries
ELSIF user_counter > 20 AND user_counter < 30 THEN
                    INSERT INTO fraudulent_transactions
                    (id, identifier, user_id, amount, timestamp, country, lat_coord, long_coord)
                    VALUES (nextval('fraudulent_transactions_seq'),
                            md5(random()::text),
                            user_counter::text,
                            random() * 1000,
                            now() - interval '9 minutes',
                            'FR',
                            48.8566,
                            2.3522);

INSERT INTO fraudulent_transactions
(id, identifier, user_id, amount, timestamp, country, lat_coord, long_coord)
VALUES (nextval('fraudulent_transactions_seq'),
        md5(random()::text),
        user_counter::text,
        random() * 1000,
        now() - interval '8 minutes',
        'UK',
        51.5074,
        -0.1278);

INSERT INTO fraudulent_transactions
(id, identifier, user_id, amount, timestamp, country, lat_coord, long_coord)
VALUES (nextval('fraudulent_transactions_seq'),
        md5(random()::text),
        user_counter::text,
        random() * 1000,
        now() - interval '7 minutes',
        'USA',
        40.7128,
        -74.0060);

-- "Normal transactions"
ELSE
                    FOR record_counter IN 1..100 LOOP
                            INSERT INTO fraudulent_transactions
                            (id, identifier, user_id, amount, timestamp, country, lat_coord, long_coord)
                            VALUES (nextval('fraudulent_transactions_seq'),
                                    md5(random()::text),
                                    user_counter::text,
                                    random() * 1000,
                                    now() - (random() * interval '30 days'),
                                    'BG',
                                    random() * 180 - 90,
                                    random() * 360 - 180);
END LOOP;
END IF;
END LOOP;
END $$;
