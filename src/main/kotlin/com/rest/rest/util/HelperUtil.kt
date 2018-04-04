package com.rest.rest.util

import com.github.jknack.handlebars.Options
import org.slf4j.LoggerFactory
import pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper
import java.util.*

@HandlebarsHelper
class HelperUtil {
    private val log = LoggerFactory.getLogger(HelperUtil::class.java)

    init {
        log.info("init")
    }

    fun default(value: String?, options: Options?): CharSequence {
        return Optional.ofNullable(value).orElse(options?.param(0, "") ?: "")
    }

}