package com.example.practiceSpring;

import com.example.practiceSpring.domain.model.Product;
import com.example.practiceSpring.reponsitory.ProductReponsitory;
import com.example.practiceSpring.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest
class PracticeSpringApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	ProductReponsitory productReponsitory;

	@Autowired
	ProductService productService;

//	@Test
//	public void test() {
//		// Day là hàm unit test, viết xong có thể viết các hàm như anh đang viết để test hàm đã viết đúng chưa
//		List<Product> products = productReponsitory.findByStatusAndName("new", "");
//		System.out.println(products.size());
//	}

//	@Test
//	public void testabc() {
//		List<Product> products = productService.findPListProduct("12000.0", "ÁO Phông", 20, "new");
//		System.out.println(products.size());
//		// Hàm cần test
//		// api vẫn chạy chả ảnh hưởng gì
//		// Muốn code mới chạy ở api thì phải chạy lại oki
//	}

//	@Test
//	public void test() {
//		List<Product> products = productService.filterProduct(12000.0, "ÁO Phông", "20.0", "new");
//		System.out.println(products.size());
//	}

//	@Test
//	public void test() {
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//		LocalDate date = LocalDate.parse("02/22/2022", formatter);
//		long millis = date.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
//
//		long millisNow=System.currentTimeMillis();
//		System.out.println(millisNow);
//
//		System.out.println(millis);
//	}

//	@Test
//	public void test() {
//		productService.checkExpiryProduct("02/22/2021");
//		System.out.println("aaaaa");
//
//	}


}
