package com.rrt.adp.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.rrt.adp.model.MediaDevice;
import com.rrt.adp.model.Page;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class MediaDeviceDaoTest extends AbstractJUnit4SpringContextTests {
	
	@Resource
	private MediaDeviceDao deviceDao;
	
	private Random random;

	@Test
	public void testInsertDevice() {
		MediaDevice device = new MediaDevice();
		device.setDeviceType(MediaDevice.TYPE_SCREEN);
		device.setDeviceStatus(MediaDevice.STATUS_WORKING);
		device.setBasePrice(100);
		device.setKeyWords("key1,key2,key3");
		device.setName("中文名乱码测试");
		device.setDescription("description");
		device.setState(MediaDevice.STATE_NEW);
		device.setPlayTime(new Date());
		device.setPlayFrequency(10);
		device.setLng(120.123);
		device.setLat(30.456);
		device.setDistrictCode("3301");
		device.setAddress("address somewhere");
		device.setOwner("luanma");
		
		deviceDao.insertDevice(device);
		
	}

	@Test
	public void testDeleteDevice() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateDevice() {
		MediaDevice device = new MediaDevice();
		device.setId("MD1500729361092");
		device.setDistrictCode("3302");
		device.setState(MediaDevice.STATE_CHECKED);
		
		deviceDao.updateDevice(device);
	}

	@Test
	public void testSelectDevice() {
		MediaDevice device = deviceDao.selectDevice("MD1500729361092");
		System.out.println(device);
				
	}

	@Test
	public void testSelectDeviceList() {
		MediaDevice device = new MediaDevice();
		device.setDeviceType(MediaDevice.TYPE_SCREEN);
		device.setDeviceStatus(MediaDevice.STATUS_WORKING);
		device.setBasePrice(100);
		device.setKeyWords("key1");
		device.setDescription("description");
		device.setState(MediaDevice.STATE_NEW);
		device.setPlayTime(new Date());
		device.setPlayFrequency(10);
		device.setLng(120.123);
		device.setLat(30.456);
		device.setDistrictCode("330104");
		device.setAddress("address somewhere");
		device.setOwner("rrtgg");
		List<MediaDevice> devcies = deviceDao.selectDeviceList(device);
		devcies.stream().forEach(System.out::println);
	}
	
	@Ignore
	public void testSelectUserDeviceList() {
		List<MediaDevice> devices = deviceDao.selectUserDeviceList("rrtgg");
		devices.stream().forEach(System.out::println);
	}
	
	@Test
	public void testSelectPage(){
		MediaDevice device = new MediaDevice();
		Page<?> page = new Page<>(1,3);
		device.setDeviceType(MediaDevice.TYPE_SCREEN);
		device.setDeviceStatus(MediaDevice.STATUS_WORKING);
		device.setBasePrice(100);
		device.setKeyWords("key1");
		device.setDescription("description");
		device.setState(MediaDevice.STATE_NEW);
		device.setPlayTime(new Date());
		device.setPlayFrequency(10);
		device.setLng(120.123f);
		device.setLat(30.456f);
		device.setDistrictCode("330104");
		device.setAddress("address somewhere");
		device.setOwner("rrtgg");
		deviceDao.selectDeviceList(device, page).stream().forEach(System.out::println);
	}
	
	@Ignore
	public void testCount(){
		MediaDevice device = new MediaDevice();
		device.setOwner("rrtgg");
		int count = deviceDao.countDevice(device);
		System.out.println(count);
	}
	
	@Test
	public void testSelectDeviceListOrderByOrder(){
		MediaDevice device = new MediaDevice();
		Page<?> page = new Page<>(1,3);
		deviceDao.selectDeviceListOrderByOrder(device, page).stream().forEach(System.out::println);
	}

}
