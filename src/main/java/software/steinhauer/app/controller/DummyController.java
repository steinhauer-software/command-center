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

package software.steinhauer.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import software.steinhauer.app.config.LdapAuthConfigModel;

import javax.inject.Inject;

@RestController
@Slf4j
public class DummyController {

	private final LdapAuthConfigModel ldapConfig;

	@Inject
	public DummyController(LdapAuthConfigModel ldapConfig) {
		this.ldapConfig = ldapConfig;
	}

	@GetMapping("/dummy")
	public String foo() {
		LOG.error("{}", ldapConfig);
		return "foo";
	}
}
