package no.nav.familie.eksterne.kontrakter.skatteetaten

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

/**
 *
 * @param identer Liste over fødselsnumre det ønskes opplysninger om.
 */
data class SkatteetatenPerioderRequest(
    @get:NotNull
    @field:JsonProperty("aar") val aar: String,
    @get:NotNull
    @field:JsonProperty("identer") val identer: List<String>,
)
