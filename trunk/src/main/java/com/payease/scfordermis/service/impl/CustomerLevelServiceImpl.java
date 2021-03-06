package com.payease.scfordermis.service.impl;

import com.payease.scfordermis.bean.Status;
import com.payease.scfordermis.bo.ResultBo;
import com.payease.scfordermis.bo.responseBo.RespLevelBean;
import com.payease.scfordermis.bo.responseBo.RespLoginPCBean;
import com.payease.scfordermis.dao.CustomerLevelDao;
import com.payease.scfordermis.entity.TConsumerInfoEntity;
import com.payease.scfordermis.entity.TConsumerLevelEntity;
import com.payease.scfordermis.service.CustomerLevelService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2018/1/10.
 */
@Service
public class CustomerLevelServiceImpl implements CustomerLevelService{

    private static final Logger log = LoggerFactory.getLogger(CustomerLevelServiceImpl.class);

    @Autowired
    EntityManager em;

    @Autowired
    CustomerLevelDao customerLevelDao;


    /**
     * 获取所有客户级别
     * @param
     * @return
     */
    @Override
    public List<TConsumerLevelEntity> findAll(long fCompanyId)throws Exception{
        try {
            return customerLevelDao.findByFCompanyId(fCompanyId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("CustomerLevelServiceImpl--findAll",e);
            throw e;
        }
    }
    /**
     * 客户级别添加和修改
     * @param
     * @return
     */
    @Override
    public ResultBo saveAndUpdate(HttpSession session, RespLevelBean respLevelBean)throws Exception{
        ResultBo result = ResultBo.build();
        try {
            if (StringUtils.isNotBlank(respLevelBean.getfName())) {
                TConsumerLevelEntity tConsumerLevelEntity = new TConsumerLevelEntity();
                RespLoginPCBean resp = (RespLoginPCBean)session.getAttribute("userInfo");
                tConsumerLevelEntity.setfOperate(resp.getfId());//操作人
                tConsumerLevelEntity.setfCompanyId(resp.getfCompanyId());//公司id
                tConsumerLevelEntity.setfId(respLevelBean.getfId());
                tConsumerLevelEntity.setfName(respLevelBean.getfName());
                List<TConsumerLevelEntity> leve =customerLevelDao.findByFNameAndFCompanyId(respLevelBean.getfName(),resp.getfCompanyId());
                if (leve.size()==0){
                //为修改操作
                if (0 != respLevelBean.getfId()) {
                    tConsumerLevelEntity.setfUpdateTime(new Date());
                    customerLevelDao.save(tConsumerLevelEntity);
                } else {//新增操作
                        tConsumerLevelEntity.setfCreateTime(new Date());
                        customerLevelDao.save(tConsumerLevelEntity);
                 }
                }else {
                    return result.setCodeId(Status.CUSTOMERLEVELNAMEALREADYEXISTS);
                }
                    return  result.setCodeId(Status.SUCCESS);
            }else{
                    return  result.setCodeId(Status.CUSTOMERLEVELCANNOTBEEMPTY);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("CustomerLevelServiceImpl--saveAndUpdate",e);
            throw e;
        }
    }

    /**
     * 删除客户级别
     * @param
     * @return
     */
    @Override
    public  void LiDelete(long fid)throws Exception{
        try {
            customerLevelDao.delete(fid);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("CustomerLevelServiceImpl--LiDelete",e);
            throw e;
        }
    }
}
