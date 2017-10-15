package com.rrt.adp.service.support;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.print.attribute.standard.Media;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.VelocityConfigurerBeanDefinitionParser;

import com.rrt.adp.model.Advertisement;
import com.rrt.adp.model.MediaDevice;
import com.rrt.adp.service.AdPlayService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class AdPlayServiceTest extends AbstractJUnit4SpringContextTests {
	
	@Resource
	private AdPlayService playService;
	private String[] devices = {"32004"};

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void test() {
		Advertisement ad1 = new Advertisement();
		ad1.setContentUrl("http://seopic.699pic.com/photo/50035/1137.jpg_wh1200.jpg");
		Advertisement ad2 = new Advertisement();
		ad2.setContentUrl("http://47.92.100.40/adfile/test.jpg");
		Advertisement ad3 = new Advertisement();
		ad3.setContentUrl("http://47.92.100.40/adfile/AD1508061974321/contentUrl/0/1507460363514.mp4");
		Advertisement ad4 = new Advertisement();
		ad4.setContentUrl("http://47.92.100.40/adfile/AD1508061802391/contentUrl/0/magazine-unlock-04-2.3.698-_98f8e746737c44408650be0967500fa1.jpg");
		List<Advertisement> ads = new ArrayList<>();
		ads.add(ad1);
		//ads.add(ad2);
		//ads.add(ad3);
		//ads.add(ad4);
		playService.play(ads, new MediaDevice());
	}
	
	@Ignore
	public void testAdd(){
		//List<String> playIds = Arrays.asList(this.devices);
		Set<String> playIds = new HashSet<>();
		playIds.addAll(Arrays.asList(this.devices));
		List<MediaDevice> devices = new ArrayList<>();
		MediaDevice device = new MediaDevice();
		device.setPlayId("32004");
		devices.add(device);
		if(null!=devices&&devices.size()>0){
			playIds.addAll(devices.stream().map(MediaDevice::getPlayId)
					.filter((t)->null!=t).collect(Collectors.toList())) ;
		}
		System.out.println(playIds);
	}
	
	@Ignore
	public void testBindDevice(){
		MediaDevice device = new MediaDevice();
		device.setForeignId("YB030971");
		device.setSerialNumber("SN9506608320");
		System.out.println(playService.bindDevice(device));
	}
		
		
	@Ignore
	public void testAdPlayServiceImpl(){
		AdPlayServiceImpl playService = new AdPlayServiceImpl();
		
		List<String> urls = new ArrayList<>();
		urls.add("http://imgs.yunbiaowulian.com/imgserver/resource/common/img/yq0KXFZz8JCAC9WpAAHK838qRuw734.jpg");
		urls.add("http://imgs.yunbiaowulian.com/imgserver/resource/common/img/yq0KZVZz70OAJrxhAAGl8Dhd6yw772.jpg");
		/*urls.add("http://imgs.yunbiaowulian.com/imgserver/resource/2017/10/08/d58b8905-5b93-47fa-95f0-64c7ef8680c7.mp4");
		urls.add("http://imgs.yunbiaowulian.com/imgserver/resource/2017/06/12/cc9f6ee7-4b96-406f-a10e-4493d88a04e8.jpg");
		urls.add("http://imgs.yunbiaowulian.com/imgserver/resource/2017/06/12/28ddffe8-96ef-4e4a-a942-11fbd93d3fb0.jpg");
		urls.add("http://imgs.yunbiaowulian.com/imgserver/resource/2017/06/12/0af55529-ec93-4626-9932-ce1fb9adea9b.jpg");
		Content content = new Content(urls);
		System.out.println(playService.jsonUtil.toJson(content));*/
		System.out.println(playService.createLayout("24379", "1012", urls));
		
		//System.out.println(playService.getDeviceList(null, "1", "1"));
		
		//System.out.println(playService.publish("24379", Arrays.asList(playService.devices)));
		
		//System.out.println(playService.getLayoutList(null, "1", "1"));
		
		//System.out.println(playService.getLayoutDetail("24379"));
	}
	
	public static String readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString.trim());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return sb.toString();
    }
	

}
