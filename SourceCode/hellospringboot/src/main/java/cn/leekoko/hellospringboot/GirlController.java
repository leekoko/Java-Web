package cn.leekoko.hellospringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GirlController {
    @Autowired
    private  GirlRepository girlRepository;

    @GetMapping(value = "/girls")
    public List<Girl> girlList(){
        return girlRepository.findAll();
    }

    /**
     * 添加内容
     * @param name
     * @param age
     * @return
     */
    @PostMapping(value="/girlAdd")
    public Girl girlAdd(@RequestParam("name") String name,@RequestParam("age") Integer age){
        Girl girl = new Girl();
        girl.setAge(age);
        girl.setName(name);

        return girlRepository.save(girl);
    }

    /**
     * 根据id查询对象
     * @param id
     * @return
     */
    @PostMapping(value="/girlById/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id){
        return girlRepository.findOne(id);
    }

    /**
     * 更新
     */
    @PutMapping(value="/moGirlById/{id}")
    public Girl girlUpdate(@PathVariable("id") Integer id,@RequestParam("name") String name,@RequestParam("age") Integer age){
        Girl girl = new Girl();
        girl.setId(id);
        girl.setName(name);
        girl.setAge(age);
        return girlRepository.save(girl);
    }

    /**
     * 删除
     */
    @DeleteMapping(value = "/delGirls/{id}")
    public void girlDelete(@PathVariable("id") Integer id){
        girlRepository.delete(id);
    }

    /**
     * 通过年龄查询
     */
    @GetMapping(value = "/girlByAge/{age}")
    public List<Girl> getListByAge(@PathVariable("age") Integer age){
        return girlRepository.findByAge(age);
    }

}
