
#global add foreign key 

alter table ParkingPosition add foreign key(parkingLotId) references ParkingLot(id);
alter table ParkingPosition add foreign key(accountId) references Account(id);

alter table Account add foreign key(userId) references User(id);
alter table Account add foreign key(parkingLotId) references ParkingLot(id);
alter table Account add foreign key(parkingPositionId) references ParkingPosition(id);

alter table Bill add foreign key(userId) references User(id);
alter table Bill add foreign key(parkingLotId) references ParkingLot(id);
alter table Bill add foreign key(accountId) references Account(id);