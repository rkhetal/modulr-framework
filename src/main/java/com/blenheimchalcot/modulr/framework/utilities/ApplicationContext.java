package com.blenheimchalcot.modulr.framework.utilities;

import lombok.Value;

import java.util.HashMap;
import java.util.Map;

@Value
public class ApplicationContext {

	public Map<Object, Object> applicationDetailsMap = new HashMap<>();


}