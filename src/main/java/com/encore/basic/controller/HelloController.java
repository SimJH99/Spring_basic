package com.encore.basic.controller;

import com.encore.basic.domain.Hello;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


//모든 요청에 ResponseBody를 붙이고 싶다면, RestController사용
// RestController는 데이터값만 보낸다.

@Controller
@RequestMapping("hello")
//클래스 차원에서 url경로를 지정하고 싶다면 @RequestMapping을 클래스 위에 선언하면서 경로지정.
public class HelloController {
    //    @reponsBody가 없고, return타입이 String이면 templates밑에 html파일 리턴
//    data만을 return할때는 @ResponseBody를 붙인다.
    @GetMapping("string")
//    @RequestMapping(value = "string", method = RequestMethod.GET)
    @ResponseBody
    public String helloString() {
        return "hello_string";
    }

    //    ObjectMapper
    @GetMapping("json")
    @ResponseBody
    public Hello helloJson() {
        Hello hello = new Hello();
        hello.setName("Sim");
        hello.setEmail("H@naver.com");
        hello.setPassword("1234");
        System.out.println(hello);
        return hello;
    }

    @GetMapping("screen")
    public String helloScreen() {
        return "screen";
    }

    @GetMapping("screen-model-param")
//    ?name=hongildong의 방식으로 호출(파라미터 호출 방식)
    public String helloScreenModelParam(Model model, @RequestParam(value = "name") String inputname) {
//        화면에 data를 넘기고 싶을때는 model객체 사용
//        model에 key:value형식으로 전달
        model.addAttribute("myData", inputname);
        return "screen";
    }

    //    pathvariable방식은 url을 통해 자원의 구조를 명확하게 표현할 수 있어, 좀더 RestFul API디자인에 적합
    @GetMapping("screen-model-path/{id}")
    public String helloScreenModelPath(Model model, @PathVariable int id) {
        model.addAttribute("myData", id);
        return "screen";
    }

    //    Form태그로 x-www 데이터 처리
//    사용자가 접속할 때, 사용자가 값을 입력하기위한 창을 먼저 받아서 해당 창에 입력하고 해당 데이터를 맵핑해서 보낸다.
//    Form태그로는 .html형식으로만 받아준다
    @GetMapping("form-screen")
    public String helloForm() {
        return "form-screen";
    }

    @PostMapping("form-post-handle")
    @ResponseBody
    public String formPostHandle(@RequestParam(value = "name") String name,
                                 @RequestParam(value = "email") String email,
                                 @RequestParam(value = "password") String password) {
        return "정상처리";
    }

    @PostMapping("form-post-handle2")
    @ResponseBody
//    Spring에서 Hello 클래스의 인스턴스를 자동 매핑하여 생성
//    forㅡdata형식 즉, x-www-url인코딩 형식의 경우 사용
//    이를 데이터 바인딩이라 부른다. (Hello클래스에 Setter 필수)
    public String formPostHandle2(Hello hello) {
        System.out.println(hello);
        return "hello-form-screen";
    }

    @PostMapping("form-post-handle3")
    @ResponseBody
    public String formPostHandle3() {
        return "정상처리";
    }

    //    json데이터 처리
//    json은 axios나 ajax형식으로 링크를 받아서 사용
    @GetMapping("json-screen")
    public String jsonScreen() {
        return "form-screen";
    }

    @PostMapping("/json-post-handle1")
    @ResponseBody
//    @RequestBody는 json으로 post요청이 들어왔을 때 body에서 data를 꺼내기 위해 사용
    public String jsonPostHandle1(@RequestBody Map<String, String> body) {
        System.out.println("이름 : " + body.get("name"));
        System.out.println("이메일 : " + body.get("email"));
        System.out.println("비밀번호 : " + body.get("password"));
        Hello hello = new Hello();
        hello.setName(body.get("name"));
        hello.setEmail(body.get("email"));
        hello.setPassword(body.get("password"));
        return "ok";
    }

    @PostMapping("/json-post-handle2")
    @ResponseBody
    public String jsonPostHandle2(@RequestBody JsonNode body) {
        Hello hello = new Hello();
        hello.setName(body.get("name").asText());
        hello.setEmail(body.get("email").asText());
        hello.setPassword(body.get("password").asText());
        return "ok";
    }

    @PostMapping("/json-post-handle3")
    @ResponseBody
    public String jsonPostHandle3(@RequestBody Hello hello) {
        System.out.println(hello);
        return "ok";
    }
}

