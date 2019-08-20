/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jsf.misc;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;

import com.jsf.bean.ThemeBean;
import com.jsf.domain.Theme;

@ManagedBean
public class ThemeSwitcherView {

	private List<Theme> themes;

	@Autowired
	private ThemeBean themeBean;

	@PostConstruct
	public void init() {
		themes = themeBean.getThemes();
	}

	public List<Theme> getThemes() {
		return themes;
	}

	public void setThemeBean(ThemeBean themeBean) {
		this.themeBean = themeBean;
	}

}
