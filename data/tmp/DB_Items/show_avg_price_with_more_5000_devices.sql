create view show_avg_price_with_more_5000_devices
as select p.name, avg(d.price)
	from devices_people dp
	join people p on dp.people_id = p.id
	join devices d on dp.device_id = d.id
	group by p.name
	having avg(d.price) > 5000

select * from show_avg_price_with_more_5000_devices