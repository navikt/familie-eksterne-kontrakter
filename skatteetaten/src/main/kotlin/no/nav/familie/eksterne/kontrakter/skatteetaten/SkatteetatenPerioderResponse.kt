package no.nav.familie.eksterne.kontrakter.skatteetaten

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 *
 * @param brukere
 */
data class SkatteetatenPerioderResponse(

    @field:Valid
    @field:JsonProperty("brukere") val brukere: List<SkatteetatenPerioder> = emptyList()
)

