CREATE TABLE `user`(
    username VARCHAR(35) NOT NULL,
    password VARCHAR(10) NOT NULL,

    PRIMARY KEY (username)
);

CREATE TABLE `flight`(
    fid VARCHAR(10) NOT NULL,
    departure VARCHAR(35) NOT NULL,
    destination VARCHAR(35) NOT NULL,
    take_off_time DATETIME NOT NULL,
    landing_time DATETIME NOT NULL,
    total_seats DECIMAL NOT NULL,
    available_seats DECIMAL NOT NULL,
    sell_status VARCHAR(10) NOT NULL,
    -- status: SELLING, SOLD OUT
    price DECIMAL NOT NULL,
    -- price is a double

    PRIMARY KEY (fid)
);
