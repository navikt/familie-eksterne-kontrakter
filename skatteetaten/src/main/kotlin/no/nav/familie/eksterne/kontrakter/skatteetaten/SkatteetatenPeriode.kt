package no.nav.familie.eksterne.kontrakter.skatteetaten

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

/**
 * Representerer en tidsperiode gitt ved en fra-og-med-måned og en valgfri til-og-med-måned, og som i tillegg inneholder en opplysning ang. maks delingsprosent i perioden
 * @param fraMaaned Første måned i perioden.
 * @param delingsprosent For perioder som løper i nytt fagsystem, vil \"delingsprosent\" alltid være \"0\" eller \"50\". \"usikker\" tilsvarer kode \"3\" i gammelt fagsystem
 * @param tomMaaned Den siste måneden i perioden
 */
data class SkatteetatenPeriode(

    @get:NotNull @field:JsonProperty("fraMaaned") val fraMaaned: String,

    @get:NotNull @field:JsonProperty("delingsprosent") val delingsprosent: Delingsprosent,

    @field:JsonProperty("tomMaaned") val tomMaaned: String? = null
) {

    /**
     * For perioder som løper i nytt fagsystem, vil \"delingsprosent\" alltid være \"0\" eller \"50\". \"usikker\" tilsvarer kode \"3\" i gammelt fagsystem
     * Values: _0,_50,usikker
     */
    enum class Delingsprosent(val value: String) {

        @JsonProperty("0") _0("0"),

        @JsonProperty("50") _50("50"),

        @JsonProperty("usikker") usikker("usikker");
    }
}
