CREATE TABLE `user`(
    username VARCHAR(35) NOT NULL,
    password VARCHAR(10) NOT NULL,

    PRIMARY KEY (username)
);

CREATE TABLE `flight`(
    fid VARCHAR(10) NOT NULL,
    departure VARCHAR(35) NOT NULL,
    destination VARCHAR(35) NOT NULL,
    take_off_time VARCHAR(20) NOT NULL,
    landing_time VARCHAR(20) NOT NULL,
    total_seats VARCHAR(10) NOT NULL,
    available_seats VARCHAR(10) NOT NULL,
    sell_status VARCHAR(10) NOT NULL,
    -- status: SELLING, SOLD OUT
    price DECIMAL NOT NULL,

    PRIMARY KEY (fid)
);
