package com.nexient.orgchart.data.entity;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.assertEquals;

/**
 * Created by kskronek on 6/9/2016.
 */
public class BaseEntityTest {

	private BaseEntity baseEntity;

	private static final Random RANDOM = new Random();

	@BeforeClass
	public void beforeClass() {
		baseEntity = new BaseEntity();
	}

	@Test
	public void setAndGetId() {
		final int id = RANDOM.nextInt();
		baseEntity.setId(id);
		Assert.assertEquals(id, Math.toIntExact(baseEntity.getId()));
	}

	@Test
	public void setAndGetIsActive(){
		baseEntity.setIsActive(true);
		Assert.assertTrue(baseEntity.getIsActive());
	}
}
