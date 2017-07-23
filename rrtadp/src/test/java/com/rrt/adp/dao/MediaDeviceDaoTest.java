package com.rrt.adp.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.print.attribute.standard.Media;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.rrt.adp.model.MediaDevice;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class MediaDeviceDaoTest extends AbstractJUnit4SpringContextTests {
	
	@Resource
	private MediaDeviceDao deviceDao;
	
	private Random random;
	private SqlProvider sqlProvider;

	@Before
	public void setUp() throws Exception {
		random = new Random(System.currentTimeMillis());
		sqlProvider = new SqlProvider();
	}

	@Ignore
	public void testInsertDevice() {
		MediaDevice device = new MediaDevice();
		device.setDeviceType("T");
		device.setDeviceStatus(MediaDevice.STATUS_WORKING);
		device.setBasePrice(100);
		device.setKeyWords("key1,key2,key3");
		device.setDescription("description");
		device.setState(MediaDevice.STATE_NEW);
		device.setPlayTime(new Date());
		device.setPlayFrequency(10);
		device.setLng(120.123f);
		device.setLat(30.456f);
		device.setDistrictCode("3301");
		device.setAddress("address somewhere");
		device.setOwner("rrtgg");
		
		deviceDao.insertDevice(device);
		
	}

	@Ignore
	public void testDeleteDevice() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testUpdateDevice() {
		MediaDevice device = new MediaDevice();
		device.setId("MD1500729361092");
		device.setDistrictCode("3302");
		device.setState(MediaDevice.STATE_CHECKED);
		
		deviceDao.updateDevice(device);
	}

	@Ignore
	public void testSelectDevice() {
		MediaDevice device = deviceDao.selectDevice("MD1500729361092");
		System.out.println(device);
				
	}

	@Ignore
	public void testSelectDeviceList() {
		List<MediaDevice> devcies = deviceDao.selectDeviceList();
		devcies.stream().forEach(System.out::println);
	}
	
	@Test
	public void testSelectUserDeviceList() {
		List<MediaDevice> devices = deviceDao.selectUserDeviceList("rrtgg");
		devices.stream().forEach(System.out::println);
	}

}
