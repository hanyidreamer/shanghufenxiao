package com.payease.scfordermis.controller;

import com.payease.scfordermis.bean.Status;
import com.payease.scfordermis.bo.ResultBo;
import com.payease.scfordermis.bo.requestBo.PageBean;
import com.payease.scfordermis.bo.requestBo.ReqCustomerBean;
import com.payease.scfordermis.bo.requestBo.ReqEmpCustomerBean;
import com.payease.scfordermis.bo.requestBo.ReqMyCustomerTwoBo;
import com.payease.scfordermis.bo.responseBo.RespCustomerBean;
import com.payease.scfordermis.bo.responseBo.RespEmpCustomerBean;
import com.payease.scfordermis.bo.responseBo.RespLevelBean;
import com.payease.scfordermis.bo.responseBo.RespLoginPCBean;
import com.payease.scfordermis.entity.TConsumerInfoEntity;
import com.payease.scfordermis.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/1/10.
 */
@RestController
@RequestMapping("/customer")
@Api(tags = {"客户管理-郑强威"})
public class CustomerController {
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private  CustomerService  customerService;

    @GetMapping(value = "index")
    @ApiOperation(value = "获取所有客户信息",response = RespCustomerBean.class)
    @ApiImplicitParams({
    })
    public ResultBo getCustomerAll(ReqCustomerBean reqCustomerBean,HttpSession session) {
        RespLoginPCBean resp = (RespLoginPCBean)session.getAttribute("userInfo");
        ResultBo result = ResultBo.build();
        try {
            if (null!=resp){
                return customerService.selectIndext(reqCustomerBean,session);
            }else {
                result.timeOut();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getCustomerAll",e);
            result.fail();
        }
        return result;
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "客户删除接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fid",value = "客户id",paramType = "query"),
    })
    public ResultBo deleteCustomerA(HttpSession session,String fid) {
        ResultBo result = ResultBo.build();
        try {
            RespLoginPCBean resp = (RespLoginPCBean)session.getAttribute("userInfo");
            if (null!=resp) {
                if (fid == null || fid.isEmpty()) {
                    result.setCodeId(Status.CUSTOMERIDCANNOTBEEMPTY);
                } else {
                    customerService.isDelete(session, Long.valueOf(fid));
                    result.setCodeId(Status.SUCCESS);
                }
            }else {
                result.timeOut();
            }
        } catch (Exception e) {
               e.printStackTrace();
              log.error("deleteCustomerA",e);
               result.fail();
        }
        return result;
    }

    @GetMapping(value = "details")
    @ApiOperation(value = "获取客户详情",response = ResultBo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fid",value = "客户id",paramType = "query"),
    })
    public ResultBo getDetails(String fid) {
        ResultBo result = ResultBo.build();
        try {
            if (StringUtils.isNotBlank(fid)){
              return  customerService.getDetail(Long.valueOf(fid));
            }else {
                result.setCodeId(Status.CUSTOMERIDCANNOTBEEMPTY);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getDetails",e);
            result.fail();
        }
        return result;
    }

    @PostMapping(value = "upsave")
    @ApiOperation(value = "添加修改客户信息接口",response = ResultBo.class)
    @ApiImplicitParams({
    })
    public ResultBo upAndSave(HttpSession session,ReqMyCustomerTwoBo reqMyCustomerTwoBo) {
        RespLoginPCBean resp = (RespLoginPCBean)session.getAttribute("userInfo");
        ResultBo result = ResultBo.build();
        try {
           if (null!=resp){
               return customerService.upAndSave(session,reqMyCustomerTwoBo);
           }else {
               result.timeOut();
           }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("upAndSave",e);
            result.fail();
        }
        return result;
    }


    //@GetMapping(value = "index")
    //@ApiOperation(value = "获取所有客户信息",response = RespEmpCustomerBean.class)
    //@ApiImplicitParams({
    //})
    //public ResultBo getCustomerAll(ReqCustomerBean ReqCustomerBean) {
    //    ResultBo result = ResultBo.build();
    //    RespEmpCustomerBean respEmpCustomerBean = new RespEmpCustomerBean();
    //    List<RespEmpCustomerBean> cusList  = new ArrayList<>();
    //    respEmpCustomerBean.setfId(Long.valueOf(1));
    //    respEmpCustomerBean.setCodeName("zhangsan123");
    //    respEmpCustomerBean.setfAreaIdOneAndTwo("天津塘沽");
    //    respEmpCustomerBean.setfCode("00001");
    //    respEmpCustomerBean.setfContact("张三");
    //    respEmpCustomerBean.setfPhone("1337640612");
    //    respEmpCustomerBean.setfName("东盟水果");
    //    cusList.add(respEmpCustomerBean);
    //    RespCustomerBean respCustomerBean = new RespCustomerBean();
    //    List<Object> mycuList = new ArrayList<>(cusList);
    //    respCustomerBean.setList(mycuList);
    //    respCustomerBean.setNumber(1);
    //    respCustomerBean.setSize(5);
    //    respCustomerBean.setTotalElements(1);
    //    respCustomerBean.setTotalPages(1);
    //    result.setResultBody(respCustomerBean);
    //    return result;
    //}
    //
    //@DeleteMapping(value = "delete")
    //@ApiOperation(value = "客户删除接口")
    //@ApiImplicitParams({
    //        @ApiImplicitParam(name = "fid",value = "客户id",paramType = "query"),
    //})
    //public ResultBo deleteCustomerA(String fid) {
    //    ResultBo result = ResultBo.build();
    //    if (fid.isEmpty()){
    //        result.setResultBody("id不能为空");
    //    }else {
    //        result.setResultBody("删除成功");
    //    }
    //    return result;
    //}
    //
    //@GetMapping(value = "details")
    //@ApiOperation(value = "获取客户详情",response = RespEmpCustomerBean.class)
    //@ApiImplicitParams({
    //        @ApiImplicitParam(name = "fid",value = "客户id",paramType = "query"),
    //})
    //public ResultBo getDetails(String fid) {
    //    ResultBo result = ResultBo.build();
    //    RespEmpCustomerBean respEmpCustomerBean = new RespEmpCustomerBean();
    //    respEmpCustomerBean.setfId(Long.valueOf(1));
    //    respEmpCustomerBean.setfName("张三");
    //    respEmpCustomerBean.setfCode("00001");
    //    respEmpCustomerBean.setCodeName("zhangsan123");
    //    respEmpCustomerBean.setfAreaIdOneAndTwo("天津塘沽");
    //    respEmpCustomerBean.setfContact("李四");
    //    respEmpCustomerBean.setfPhone("13888888888");
    //    result.setResultBody(respEmpCustomerBean);
    //    return result;
    //}
    //
    //@PostMapping(value = "upsave")
    //@ApiOperation(value = "添加修改客户信息接口",response = ResultBo.class)
    //@ApiImplicitParams({
    //})
    //public ResultBo getDetails(ReqEmpCustomerBean reqEmpCustomerBean) {
    //    ResultBo result = ResultBo.build();
    //    result.setResultBody("保存成功");
    //    return result;
    //}
    //
    //@PutMapping(value = "resetpassword")
    //@ApiOperation(value = "重置客户账号的密码")
    //@ApiImplicitParams({
    //        @ApiImplicitParam(name = "findentno",value = "客户账号",paramType = "query"),
    //        @ApiImplicitParam(name = "findpassword",value = "客户密码",paramType = "query"),
    //})
    //public ResultBo resetPassword(String findentno,String findpassword) {
    //    ResultBo result = ResultBo.build();
    //    result.setResultBody("重置密码成功");
    //    return result;
    //}
}

