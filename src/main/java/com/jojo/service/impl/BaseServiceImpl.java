package com.jojo.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jojo.service.BaseService;
import com.jojo.util.SnowFlakerUtil;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

public class BaseServiceImpl<T> implements BaseService<T> {

	/**
	 * 通用mapper
	 */
	@Autowired
	private Mapper<T> mapper;

	/**
	 * 泛型的Class
	 */
	private Class<T> classOfT;

	/**
	 * 构造函数，获取泛型的Class类型
	 */
	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		classOfT = (Class<T>) params[0];
	}

	@Override
	public Example buildExample() {
		return new Example(classOfT);
	}

	@Override
	public int insert(T entity) {
		setId(entity);
		return mapper.insert(entity);
	}

	@Override
	public int insertSelective(T entity) {
		setId(entity);
		return mapper.insertSelective(entity);
	}

	private void setId(T entity) {
		try {
			Field field = classOfT.getDeclaredField("id");
			Long valueOfId = (Long) field.get(entity);
			if (valueOfId == null || valueOfId == 0L) {
				valueOfId = SnowFlakerUtil.getSnowflakeId();
				field.set(entity, valueOfId);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int deleteByExample(Object example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public int selectCountByExample(Object example) {
		return mapper.selectCountByExample(example);
	}

	@Override
	public int selectCount(T entity) {
		return mapper.selectCount(entity);
	}

	@Override
	public T selectOneByPrimaryKey(Object key) {
		return mapper.selectByPrimaryKey(key);
	}

	/**
	 * 这东西吧，插件没有自己实现，查一个的功能是靠分页模拟出来的，返回第一页的第一个
	 */
	@Override
	public T selectOneByExample(Object example) {
		return null;
	}

	@Override
	public List<T> selectByExample(Object example) {
		return mapper.selectByExample(example);
	}

	@Override
	public List<T> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(T entity) {
		return mapper.updateByPrimaryKey(entity);
	}

	@Override
	public int updateByPrimaryKeySelective(T entity) {
		return mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public int updateByExample(T entity, Object example) {
		return mapper.updateByExample(entity, example);
	}

	@Override
	public int updateByExampleSelective(T entity, Object example) {
		return mapper.updateByExampleSelective(entity, example);
	}

}
