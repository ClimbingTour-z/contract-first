package com.one.controller;

import com.one.model.Compact;
import com.one.util.Page;
import com.one.serivce.CompactService;
import com.one.util.ImportExcelUtil;
import com.one.util.VerifyExcelVersionUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "excel")
public class CompactController {
    @Autowired
    private CompactService compactService;

    private static Log log = LogFactory.getLog(CompactController.class);

    /**
     * 上传页面
     * @return
     */
    @RequestMapping(value="/imExcel")
    public String imExcel(){
        System.out.println("上传页面。");
        return "/excel/imExcel";
    }

    /**
     * 合同详情页面
     * @return
     */
    @RequestMapping(value="/compact")
    public String compact(){
        System.out.println("合同详情页面。");
        return "/excel/compact";
    }

    /**
     * 上传Excel,读取Excel中内容
     * @param file
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/uploadExcel",method = RequestMethod.POST)
    public String uploadExcel(@RequestParam(value="filename") MultipartFile file,
                                HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("CompactController ..uploadExcel() start");
        String Msg =null;
        boolean b = false;

        //判断文件是否为空
        if(file==null){
            Msg ="文件是为空！";
            request.getSession().setAttribute("msg",Msg);
            return "/excel/imExcel";
        }

        //获取文件名
        String name=file.getOriginalFilename();

        //进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）验证文件名是否合格
        long size=file.getSize();
        if(name==null || ("").equals(name) && size==0 && !VerifyExcelVersionUtil.validateExcel(name)){
            Msg ="文件格式不正确！请使用.xls或.xlsx后缀文档。";
            request.getSession().setAttribute("msg",Msg);
            return "/excel/imExcel";
        }

        //创建处理EXCEL
        ImportExcelUtil importExcelUtil=new ImportExcelUtil();
        //解析excel，获取客户信息集合。
        List<Compact> compactList = importExcelUtil.getExcelInfo(name,file);
        if(compactList != null && !compactList.toString().equals("[]") && compactList.size()>=1){
            b = true;
        }

        if(b){
            //迭代添加客户信息（注：实际上这里也可以直接将customerList集合作为参数，在Mybatis的相应映射文件中使用foreach标签进行批量添加。）
            for(Compact compact : compactList){
                //这里可以做添加数据库的功能
                // System.out.println("第一个值："+customer.getCustomer1()+"\t第二个值："+customer.getCustomer2()+"\t第三个值："+customer.getCustomer3());
                compactService.save(compact);
                System.out.print(compact);
            }
            Msg ="批量导入EXCEL成功！";
            request.getSession().setAttribute("msg",Msg);
        }else{
            Msg ="批量导入EXCEL失败！";
            request.getSession().setAttribute("msg",Msg);
        }
        return "/excel/imExcel";
    }


    /**
     * 合同详情Table表
     * @param compact
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "List")
    public String list(@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="10")Integer rows,Compact compact, HttpServletRequest request, HttpServletResponse response, Model model) {
       // Page<Compact> compactList = compactService.findList(new Page<Compact>(request, response),compact);
        Page<Compact> compactList = compactService.findList1(page,rows,compact);
        model.addAttribute("page",compactList);
        //Page<Compact> pageList = compactService.findPage(new Page<Compact>(request, response), compact);
        //model.addAttribute("pageList", pageList);
        return "/excel/compact";
    }
    

    /**
     * 删除反馈Flash信息
     * @param redirectAttributes
     * @param messages
     */
    protected void deleteMessage(RedirectAttributes redirectAttributes, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages){
            sb.append(message).append(messages.length>1?"<br/>":"");
        }
        redirectAttributes.addFlashAttribute("message", sb.toString());
    }

    /**
     * 删除
     * @param compact
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "delete")
    public String delete(Compact compact, RedirectAttributes redirectAttributes) {
        compactService.delete(compact);
        deleteMessage(redirectAttributes, "删除值班信息成功");
        return "redirect:/excel/List";
    }
}
