package org.example.routtoproject.controller.shop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.routtoproject.model.entity.shop.Product;
import org.example.routtoproject.service.shop.ProductService;

import org.springframework.http.HttpHeaders;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * packageName : org.example.routtoproject.controller.shop
 * fileName : ProductController
 * author : hayj6
 * date : 2024-04-22(022)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-22(022)         hayj6          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/shop")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;        // DI

    // todo 전체조회 함수

    @GetMapping("/product/all")
    public ResponseEntity<Object> getAll(){
        try {
            List<Product> products = productService.findAll();
            return new ResponseEntity<>(products,HttpStatus.OK);
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    //    TODO: 전체조회 함수
//    조회(select) -> get 방식 -> @GetMapping
    @GetMapping("/product")
    public ResponseEntity<Object> findAll(
            @RequestParam(defaultValue = "") String prodName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        try {
//            페이징 객체 생성
            Pageable pageable = PageRequest.of(page, size);

//            전체 조회 서비스 실행
            Page<Product> products
                    = productService
                    .findAllByProdNameContaining(prodName, pageable);

//            공통 페이징 객체 생성 : 자료구조 맵 사용
            Map<String , Object> response = new HashMap<>();
            response.put("product", products.getContent());    // simpleProduct 배열
            response.put("currentPage", products.getNumber());       // 현재페이지번호
            response.put("totalItems", products.getTotalElements()); // 총건수(개수)
            response.put("totalPages", products.getTotalPages());    // 총페이지수

            if(products.isEmpty() == false) {
//                조회 성공
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    //       TODO: 상세 조회 함수
    @GetMapping("/product/{prodId}")
    public ResponseEntity<Object> findById(@PathVariable int prodId){
        try{
            Optional<Product> product = productService.findById(prodId);
            if (product.isEmpty() == false) {
                return new ResponseEntity<>(product.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    TODO: 파일 다운로드 함수
@GetMapping("/product/img/{uuid}")
public ResponseEntity<byte[]> findImg(@PathVariable String uuid) {
    Product product = productService.findImgByUuid(uuid).get();

    return ResponseEntity.ok()
//           Todo : attachment: => attachment;
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + product.getProdName() + "\"")
            .body(product.getProdImg());
}
@GetMapping("/product/page/{uuid}")
public ResponseEntity<byte[]> findPage(@PathVariable String uuid) {
    Product product = productService.findPageByUuid(uuid).get();

    return ResponseEntity.ok()
//           Todo : attachment: => attachment;
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + product.getProdName() + "\"")
            .body(product.getProdImg());
}
    @PostMapping("/product")
    public ResponseEntity<Object> createProduct(@RequestParam(defaultValue = "") String prodName,
                                                @RequestParam (defaultValue = "0") String defaultPrice,
                                                @RequestParam (defaultValue = "")String prodCategory,
                                                @RequestParam MultipartFile prodImg,
                                                @RequestParam MultipartFile prodDetailPage,

                                                @RequestParam (defaultValue = "0")String discountRate,
                                                @RequestParam (defaultValue = "0")String prodStock,

                                                @RequestParam (defaultValue = "")String prodImgUrl,
                                                @RequestParam (defaultValue = "")String prodDetailPageUrl
                                                ){
        try {
//
//                log.debug("확인용" + prodImg);
//                log.debug("확인용" + prodDetailPage);
                Product product1 = productService.save(
                        prodName,
                        Integer.parseInt(defaultPrice), // TODO: 정수로 변경
                        prodCategory,
                        prodImg,
                        prodDetailPage,
                        Integer.parseInt(discountRate), // TODO: 정수로 변경
                        Integer.parseInt(prodStock),    // TODO: 정수로 변경
                        prodImgUrl,
                        prodDetailPageUrl);
                return new ResponseEntity<>(/*product1,*/ HttpStatus.OK);


        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
