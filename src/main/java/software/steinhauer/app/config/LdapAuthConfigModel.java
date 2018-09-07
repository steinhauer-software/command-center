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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ConfigurationProperties(prefix = "cc.auth")
public class LdapAuthConfigModel {

	private String uri;
	private String base;
	private UserConfig user;
	private GroupConfig group;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class UserConfig {
		private String searchBase;
		private String filter;
		private String dnPatterns;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class GroupConfig {
		private String searchBase;
		private String filter;
	}

}
