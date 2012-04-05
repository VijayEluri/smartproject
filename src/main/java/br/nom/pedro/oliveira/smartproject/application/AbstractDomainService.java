/*
 *  Copyright (C) 2010 Pedro T. Oliveira <pedro.oliveira.nom.br>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.nom.pedro.oliveira.smartproject.application;

import com.ppm.infrastructure.utils.log.Severity;
import static com.ppm.infrastructure.utils.log.Severity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * This class represent a Abstract Domain Service, All domain services
 * extends this class. <p> This class implements behavior that will
 * be inherited to all services. <p> For default, domain services don't have
 * behavior, then this class will implement only infrastructure behavior needed
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 * @version 1.0
 * @since 1.0
 */
@Service
public abstract class AbstractDomainService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private void log(String msg, Severity s) {

        if (INFO.equals(s) && logger.isInfoEnabled()) {
            logger.info(msg);
        } else if (WARN.equals(s) && logger.isWarnEnabled()) {
            logger.warn(msg);
        } else if (DEBUG.equals(s) && logger.isDebugEnabled()) {
            logger.debug(msg);
        } else if (ERROR.equals(s)) {
            logger.error(msg);
        } else {
            //The Default Log Level
            logger.trace(msg);
        }
    }
}
