/* 
 * Copyright (C) 2016 attila
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */

function ScriptLoader() {}

ScriptLoader.include = function (elements) {
    for (var i = 0; i < elements.length; i++) {
        jQuery.ajax({
            url:  WEB_DIR + '/resources/js/' + elements[i] + '.js',
            dataType: "script",
            async: false,
            success: function () {
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.error(jqXHR);
                console.error(textStatus);
                console.error(errorThrown);
                throw new Error("Following element could not be loaded {0} ".format(elements[i]));
            }
        });
    }
}
