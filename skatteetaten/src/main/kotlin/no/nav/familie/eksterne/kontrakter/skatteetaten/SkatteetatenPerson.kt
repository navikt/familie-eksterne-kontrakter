package no.nav.familie.eksterne.kontrakter.skatteetaten

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import javax.validation.constraints.NotNull

/**
 *
 * @param ident Person identifikator
 * @param sisteVedtakPaaIdent Tidspunkt for siste vedtak (systemtidspunkt)
 */
data class SkatteetatenPerson(
    @get:NotNull
    @field:JsonProperty("ident") val ident: String,
    @get:NotNull
    @field:JsonProperty("sisteVedtakPaaIdent") val sisteVedtakPaaIdent: LocalDateTime,
)
