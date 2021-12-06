package no.nav.familie.eksterne.kontrakter.bisys

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.YearMonth
import javax.validation.constraints.NotNull

data class OpphørBarnetrygdBisysMelding(@get:NotNull @field:JsonProperty("personident") val personident: String,

                                        @get:NotNull @field:JsonProperty("opphørFom") val opphørFom: YearMonth)


