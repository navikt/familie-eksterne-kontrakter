package no.nav.familie.eksterne.kontrakter.skatteetaten

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 *
 * @param ident Person identifikator
 * @param sisteVedtakPaaIdent Tidspunkt for siste vedtak (systemtidspunkt)
 * @param perioder
 */
data class SkatteetatenPerioder(

    @get:NotNull
    @field:JsonProperty("ident") val ident: String,

    @get:NotNull
    @field:JsonProperty("sisteVedtakPaaIdent") val sisteVedtakPaaIdent: LocalDateTime,

    @get:NotNull
    @field:Valid
    @field:JsonProperty("perioder") val perioder: List<SkatteetatenPeriode>
)

