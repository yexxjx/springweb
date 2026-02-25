package example.day02.controller;


import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/practice2")
public class RestController4 {

    @PostMapping("/post") @ResponseBody
    public boolean method1(){return true;}

    @GetMapping("/post")
    public int method2(@RequestParam int pno, @RequestParam String ptitle){
        System.out.println("RestController4.method2");
        System.out.println("pno = " + pno + ", ptitle = " + ptitle);
        return  2;
    }


    @GetMapping("/post/view")
    public int method3(@RequestParam Map<Integer, String> map){
        System.out.println("RestController4.method3");
        System.out.println("map = " + map);
        return 3;
    }

    @PutMapping("/post") @ResponseBody
    public boolean method4(){return true;}

    @DeleteMapping("/post") @ResponseBody
    public int method5(){
        System.out.println("RestController4.method3");
        return 3;
    }
}
