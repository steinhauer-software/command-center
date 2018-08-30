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

package software.steinhauer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
