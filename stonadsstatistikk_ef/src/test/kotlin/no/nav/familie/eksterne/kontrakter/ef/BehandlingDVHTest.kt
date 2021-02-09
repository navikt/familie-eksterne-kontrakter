package no.nav.familie.eksterne.kontrakter.ef

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.ZonedDateTime
import java.util.*

class BehandlingDVHTest {

    val vedtak = BehandlingDVH(
            sakId = UUID.randomUUID().toString(),
            behandlingId = "EF-123",
            relatertBehandlingId = "EF-110",
            kode6eller7 = false,
            tidspunktVedtak = ZonedDateTime.now(),
            vilkårsvurderinger = listOf(
                    Vilkårsvurdering(Vilkår.LOVLIG_OPPHOLD,true),
                    Vilkårsvurdering(Vilkår.SIVILSTAND,true)
            ),
            person = Person(aktorId = "5634422"),
            barn = listOf(Person(aktorId = "6442433")),
            behandlingType = BehandlingType.REVURDERING,
            behandlingÅrsak = BehandlingÅrsak.SØKNAD,
            behandlingResultat = BehandlingResultat.FULLFØRT,
            vedtak = Vedtak.INNVILGET,
            utbetalinger = listOf(
                    Utbetaling(
                            PeriodeBeløp(19003, Periodetype.MÅNED,
                                         LocalDate.of(2021,3,1),
                                         LocalDate.of(2024,2,29)),
                            Utbetalingsdetalj(Person(aktorId = "5634422"),"EFOG","EF-123-EF-019")
                    )
            ),
            inntekt = listOf(),
            aktivitetskrav = Aktivitetskrav(
                    LocalDate.of(2021,12,1),
                    harSagtOppArbeidsforhold = false

            )
    )


    @Test
    fun test1() {
        val mapper = ObjectMapper().registerModule(KotlinModule())
        val serialized = mapper.writeValueAsString(vedtak)

        println(serialized)
    }

}