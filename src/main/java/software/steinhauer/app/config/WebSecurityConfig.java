/*
 * This file is part of Command Center.
 *
 * Command Center is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General  Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Command Center is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General  Public License for more details.
 *
 * You should have received a copy of the GNU Affero General  Public License
 * along with Command Center.  If not, see <https://www.gnu.org/licenses/>.
 */

package software.steinhauer.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;

import javax.inject.Inject;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final LdapAuthConfigModel ldapAuthConfig;

	@Inject
	public WebSecurityConfig(LdapAuthConfigModel ldapAuthConfig) {
		this.ldapAuthConfig = ldapAuthConfig;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
				.authorizeRequests()
					.antMatchers("/").hasAnyRole("CCUSER", "CCADMIN")
					.antMatchers("/admin").hasRole("CCADMIN")
					.anyRequest().fullyAuthenticated()
				.and()
					.formLogin();
		// @formatter:on
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BindAuthenticator bindAuthenticator = new BindAuthenticator(contextSource());
		bindAuthenticator.setUserDnPatterns(new String[]{ldapAuthConfig.getUser().getDnPatterns()});
		auth.authenticationProvider(new LdapAuthenticationProvider(bindAuthenticator));

		auth
				.ldapAuthentication()
				.userDnPatterns(ldapAuthConfig.getUser().getDnPatterns())
				.userSearchBase(ldapAuthConfig.getUser().getSearchBase())
				.userSearchFilter(ldapAuthConfig.getUser().getFilter())
				.groupSearchBase(ldapAuthConfig.getGroup().getSearchBase())
				.groupSearchFilter(ldapAuthConfig.getGroup().getFilter())
				.contextSource(contextSource());
	}

	@Bean
	public DefaultSpringSecurityContextSource contextSource() {
		return new DefaultSpringSecurityContextSource(
				Collections.singletonList(ldapAuthConfig.getUri()),
				ldapAuthConfig.getBase()
		);
	}
}
