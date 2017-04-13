package demo.repository;

import demo.entity.OauthClientDetails;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;


/**
 * oauthClientDetails表的基本操作类
 * Created by JJH on 2017/4/13.
 */
@Repository
public interface OauthClientDetailsRepository extends Mapper<OauthClientDetails> {
}
