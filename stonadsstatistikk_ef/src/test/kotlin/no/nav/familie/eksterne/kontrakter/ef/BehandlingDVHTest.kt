package no.nav.familie.eksterne.kontrakter.ef

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.ZonedDateTime
import java.util.*


class BehandlingDVHTest {

    val vedtak = BehandlingDVH(
            fagsakId = UUID.randomUUID().toString(),
            behandlingId = "EF-123",
            relatertBehandlingId = "EF-110",
            kode6eller7 = false,
            tidspunktVedtak = ZonedDateTime.now(),
            vilkårsvurderinger = listOf(
                    Vilkårsvurdering(Vilkår.LOVLIG_OPPHOLD, true),
                    Vilkårsvurdering(Vilkår.SIVILSTAND, true)
            ),
            person = Person(aktorId = "5634422"),
            barn = listOf(Person(aktorId = "6442433")),
            behandlingType = BehandlingType.REVURDERING,
            behandlingÅrsak = BehandlingÅrsak.SØKNAD,
            behandlingResultat = BehandlingResultat.FERDIGSTILT,
            vedtak = Vedtak.INNVILGET,
            utbetalinger = listOf(
                    Utbetaling(
                            PeriodeBeløp(19003, Periodetype.MÅNED,
                                         LocalDate.of(2021, 3, 1),
                                         LocalDate.of(2024, 2, 29)),
                            Utbetalingsdetalj(Person(aktorId = "5634422"), "EFOG", "EF-123-EF-019")
                    )
            ),
            inntekt = listOf(Inntekt(
                    PeriodeBeløp(0, Periodetype.MÅNED,
                                 LocalDate.of(2021, 3, 1),
                                 LocalDate.of(2024, 2, 29)
                    ), Inntektstype.ARBEIDINNTEKT)),
            aktivitetskrav = Aktivitetskrav(
                    LocalDate.of(2021, 12, 1),
                    harSagtOppArbeidsforhold = false

            )
    )


    @Test
    fun verifserSerialisering() {

        val mapper = ObjectMapper().registerModule(KotlinModule())
        mapper.registerModule(JavaTimeModule())
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        val serialized = mapper.writeValueAsString(vedtak)

        //println(serialized)
    }

}