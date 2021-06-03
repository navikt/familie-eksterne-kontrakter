package no.nav.familie.eksterne.kontrakter.ef

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
        person = Person(personIdent = "5634422"),
        barn = listOf(Person(personIdent = "6442433")),
        behandlingType = BehandlingType.REVURDERING,
        behandlingÅrsak = BehandlingÅrsak.SØKNAD,
        vedtak = Vedtak.INNVILGET,
        utbetalinger = listOf(
                    Utbetaling(
                           100,
                                         LocalDate.of(2021, 3, 1),
                                         LocalDate.of(2024, 2, 29),
                            Utbetalingsdetalj("EFOG", "EF-123-EF-019"))
                    ),
        inntekt = listOf(Inntekt(beløp = 100, samordningsfradrag = 0, fraOgMed = LocalDate.of(2021, 3, 1), tilOgMed =LocalDate.of(2024, 2, 29))),
        aktivitetskrav = Aktivitetskrav(
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