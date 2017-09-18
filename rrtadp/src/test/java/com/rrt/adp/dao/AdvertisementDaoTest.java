package com.rrt.adp.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.rrt.adp.dao.support.AdvertisementDaoImpl;
import com.rrt.adp.model.Advertisement;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class AdvertisementDaoTest extends AbstractJUnit4SpringContextTests{
	
	@Resource
	private AdvertisementDao advertisementDao;
	
	private SqlProvider sqlProvider;

	@Before
	public void setUp() throws Exception {
		sqlProvider = new SqlProvider();
	}
	
	@Test
	public void testInsertAd() {
		Advertisement advertisement = new Advertisement();
		advertisement.setTitle("ad-title");
		advertisement.setType(Advertisement.TYPE_TEXT);
		advertisement.setState(Advertisement.STATE_NEW);
		advertisement.setContent("ad-content");
		advertisement.setContentUrl("ad-content-url");
		advertisement.setTimeInSecond(10);
		advertisement.setOwner("rrtgg");
		
		advertisementDao.insertAd(advertisement);
	}

	@Ignore
	public void testDeleteAd() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateAd() {
		Advertisement advertisement = new Advertisement();
		advertisement.setId("AD1500731358736");
		advertisement.setState(Advertisement.STATE_CHECKED);
		advertisement.setContent("new content");
		advertisementDao.updateAd(advertisement);
	}

	@Test
	public void testSelectAd() {
		Advertisement advertisement = advertisementDao.selectAd("AD1500731358736");
		System.out.println(advertisement);
	}

	@Test
	public void testSelectAdList() {
		Advertisement advertisement = new Advertisement();
		advertisement.setTitle("ad-title");
		advertisement.setType(Advertisement.TYPE_TEXT);
		advertisement.setState(Advertisement.STATE_NEW);
		advertisement.setContent("ad-content");
	
		advertisement.setOwner("rrtgg");
		List<Advertisement> ads = advertisementDao.selectAdList(advertisement);
		ads.stream().forEach(System.out::println);
	}

}
