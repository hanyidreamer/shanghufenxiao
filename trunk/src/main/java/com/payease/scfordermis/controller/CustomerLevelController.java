package com.payease.scfordermis.controller;

import com.payease.scfordermis.bean.Status;
import com.payease.scfordermis.bo.ResultBo;
import com.payease.scfordermis.bo.requestBo.ReqConsumerLevelBean;
import com.payease.scfordermis.bo.responseBo.RespLevelBean;
import com.payease.scfordermis.bo.responseBo.RespLoginPCBean;
import com.payease.scfordermis.entity.TConsumerLevelEntity;
import com.payease.scfordermis.service.CustomerLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/1/11.
 */
@RestController
@RequestMapping("/level")
@Api(tags = {"客户级别-郑强威"})
public class CustomerLevelController {

    private static final Logger log = LoggerFactory.getLogger(CustomerLevelController.class);

    @Autowired
    private CustomerLevelService customerLevelService;

    @GetMapping(value = "index")
    @ApiOperation(value = "获取客户级别列表",response = RespLevelBean.class)
    @ApiImplicitParams({
    })
    public ResultBo getCustomerLevelAll(HttpSession session) {
        ResultBo result = ResultBo.build();
        try {
            RespLoginPCBean respS = (RespLoginPCBean)session.getAttribute("userInfo");
            if (null!=respS){
                long fCompanyId = respS.getfCompanyId();
                if (fCompanyId!=0){
                    List<TConsumerLevelEntity> tConsumerLevelEntities = customerLevelService.findAll(fCompanyId);
                    if (tConsumerLevelEntities!=null  && !tConsumerLevelEntities.isEmpty() ) {
                        List<RespLevelBean> resp = new ArrayList<>();
                        for (TConsumerLevelEntity Leve : tConsumerLevelEntities) {
                            RespLevelBean respLevelBean = new RespLevelBean();
                            BeanUtils.copyProperties(Leve, respLevelBean);
                            resp.add(respLevelBean);
                            result.setResultBody(resp);
                        }
                    }else{
                        result.setCodeId(Status.THISCOMPANYCUSTOMERLEVELWASNOTFOUND);
                    }
                }else {
                    result.setCodeId(Status.COMPANYIDCANNOTBEEMPTY);
                }
            }else {
                   result.timeOut();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getCustomerLevelAll",e);
            result.fail();
        }
        return result;
    }

    @PutMapping(value = "saveup")
    @ApiOperation(value = "添加修改接口")
    @ApiImplicitParams({
    })
    public ResultBo saveAndUpdate(HttpSession session, RespLevelBean  respLevelBean) {
        ResultBo result = ResultBo.build();
        try {
            RespLoginPCBean resp = (RespLoginPCBean)session.getAttribute("userInfo");
            if (null!=resp){
                return  customerLevelService.saveAndUpdate(session,respLevelBean);
            }else {
                result.timeOut();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("saveAndUpdate",e);
            result.fail();
        }
           return result;
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "删除接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fid",value = "客户级别id",paramType = "query"),
    })
    public ResultBo CustomerLevelUp(long fid) {
        ResultBo result = ResultBo.build();
        try {
            if (fid!=0){
                customerLevelService.LiDelete(fid);
                result.setCodeId(Status.SUCCESS);
            }else {
                result.setCodeId(Status.CUSTOMERLEVELIDCANNOTBEEMPTY);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.fail();
            log.error("CustomerLevelUp",e);
            result.fail();
        }
            return result;
    }


    //@GetMapping(value = "index")
    //@ApiOperation(value = "获取客户级别列表",response = RespLevelBean.class)
    //@ApiImplicitParams({
    //})
    //public ResultBo getCustomerLevelAll() {
    //    ResultBo result = ResultBo.build();
    //    RespLevelBean respLevelBean = new RespLevelBean();
    //    respLevelBean.setfId(1);
    //    respLevelBean.setfName("一级经销商");
    //    RespLevelBean respLevelBean1 = new RespLevelBean();
    //    respLevelBean1.setfId(2);
    //    respLevelBean1.setfName("二级经销商");
    //    RespLevelBean respLevelBean2 = new RespLevelBean();
    //    respLevelBean2.setfId(3);
    //    respLevelBean2.setfName("三级经销商");
    //    RespLevelBean respLevelBean3 = new RespLevelBean();
    //    respLevelBean3.setfId(4);
    //    respLevelBean3.setfName("四级经销商");
    //    List<RespLevelBean> cusList  = new ArrayList<>();
    //    cusList.add(respLevelBean);
    //    cusList.add(respLevelBean1);
    //    cusList.add(respLevelBean2);
    //    cusList.add(respLevelBean3);
    //    result.setResultBody(cusList);
    //    return result;
    //}
    //
    //@PutMapping(value = "saveup")
    //@ApiOperation(value = "添加修改接口")
    //@ApiImplicitParams({
    //})
    //public ResultBo CustomerLevelUp(HttpSession session, ReqConsumerLevelBean reqConsumerLevelBean) {
    //    ResultBo result = ResultBo.build();
    //    result.setResultBody("成功");
    //    return result;
    //}
    //
    //@DeleteMapping(value = "delete")
    //@ApiOperation(value = "删除接口")
    //@ApiImplicitParams({
    //        @ApiImplicitParam(name = "fid",value = "客户级别id",paramType = "query"),
    //})
    //public ResultBo CustomerLevelUp(int fid) {
    //    ResultBo result = ResultBo.build();
    //    result.setResultBody("删除成功");
    //    return result;
    //}

}
