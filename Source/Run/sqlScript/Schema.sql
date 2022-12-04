CREATE TABLE `user`(
    username VARCHAR(35) NOT NULL,
    password VARCHAR(10) NOT NULL,

    PRIMARY KEY (username)
);

CREATE TABLE `flight`(
    flight_index INT AUTO_INCREMENT,
    fid VARCHAR(10) NOT NULL,
    departure VARCHAR(35) NOT NULL,
    destination VARCHAR(35) NOT NULL,
    take_off_time VARCHAR(20) NOT NULL,
    landing_time VARCHAR(20) NOT NULL,
    total_seats DECIMAL NOT NULL,
    available_seats DECIMAL NOT NULL,
    sell_status VARCHAR(10) NOT NULL,
    -- status: SELLING, SOLD OUT
    price DECIMAL NOT NULL,
    -- price is a integer

    PRIMARY KEY (flight_index)
);

CREATE TABLE `order`(
    order_index INT AUTO_INCREMENT,
    flight_set VARCHAR(255) NOT NULL,
    number DECIMAL NOT NULL,
    username VARCHAR(10) NOT NULL,

    PRIMARY KEY (order_index)
);

TRUNCATE TABLE flight;
