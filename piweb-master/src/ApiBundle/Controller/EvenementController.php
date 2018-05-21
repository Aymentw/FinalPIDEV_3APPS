<?php
/**
 * Created by PhpStorm.
 * User: Mechlaoui
 * Date: 26/04/2018
 * Time: 20:21
 */

namespace ApiBundle\Controller;
use Doctrine\Common\Collections\ArrayCollection;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\Config\Definition\Exception\Exception;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use UtilisateurBundle\Entity\Evenement;
use UtilisateurBundle\Entity\Reservation;
use UtilisateurBundle\Entity\User;
use Symfony\Component\HttpFoundation\BinaryFileResponse;


class EvenementController extends Controller
{
    public function loginAction(Request $request)
    {
        $username = $request->query->get("username");
        $password = $request->query->get("password");
        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository("UtilisateurBundle:User")->findOneBy(['username' => $username]);
        $user->setPlainPassword($user->getPlainPassword());
        if ($user) {
            if (password_verify($password, $user->getPassword())) {
                $serializer = new Serializer([new ObjectNormalizer()]);
                $formatted = $serializer->normalize($user);
                return new JsonResponse($formatted);
            } else {
                return new Response("failed");
            }
        } else {
            return new Response("failed");
        }

    }

    public function registerAction(Request $request)
    {

        $username = $request->query->get("username");
        $password = $request->query->get("password");
        $email = $request->query->get("email");
        $niveau = $request->query->get("niveau");

        $user = new User();
        $user->setUsername($username);
        $user->setPlainPassword($password);
        $user->setEmail($email);
        $user->setNiveau($niveau);
        $user->setEnabled(true);

        try {

            $em = $this->getDoctrine()->getManager();
            $em->persist($user);
            $em->flush();
            return new Response("success");

        } catch (Exception $ex) {
            return new Response("fail");
        }
    }

    public function findWeekAction()
    {
        $em = $this->getDoctrine()->getEntityManager();
        $events = $em->getRepository('UtilisateurBundle:Evenement')->findWeek();
        foreach ($events as $event) {
            $dispo = $event->getNombre() - count($em->getRepository('UtilisateurBundle:Reservation')->findBy(["idEvenement" => $event, "etat" => "Confirmé"]));
            $event->setDisponible($dispo);
        }

        if ($events) {
            $encoder = new JsonEncoder();
            $normalizer = new ObjectNormalizer();
            $normalizer->setCircularReferenceHandler(function ($object) {
                return $object->getId();
            });
            $serializer = new Serializer([$normalizer], [$encoder]);
            $formatted = $serializer->normalize($events);
            return new JsonResponse($formatted);
        } else {
            return new Response("no data");
        }
    }

    public function findAllAction()
    {
        $em = $this->getDoctrine()->getEntityManager();
        $events = $em->getRepository('UtilisateurBundle:Evenement')->findAll();
        foreach ($events as $event) {
            $dispo = $event->getNombre() - count($em->getRepository('UtilisateurBundle:Reservation')->findBy(["idEvenement" => $event, "etat" => "Confirmé"]));
            $event->setDisponible($dispo);
        }

        if ($events) {
            $encoder = new JsonEncoder();
            $normalizer = new ObjectNormalizer();
            $normalizer->setCircularReferenceHandler(function ($object) {
                return $object->getId();
            });
            $serializer = new Serializer([$normalizer], [$encoder]);
            $formatted = $serializer->normalize($events);
            return new JsonResponse($formatted);
        } else {
            return new Response("no data");
        }
    }

    public function findCurrentAction()
    {
        $em = $this->getDoctrine()->getEntityManager();
        $events = $em->getRepository('UtilisateurBundle:Evenement')->CurrentGreater();
        foreach ($events as $event) {
            $dispo = $event->getNombre() - count($em->getRepository('UtilisateurBundle:Reservation')->findBy(["idEvenement" => $event, "etat" => "Confirmé"]));
            $event->setDisponible($dispo);
        }

        if ($events) {
            $encoder = new JsonEncoder();
            $normalizer = new ObjectNormalizer();
            $normalizer->setCircularReferenceHandler(function ($object) {
                return $object->getId();
            });
            $serializer = new Serializer([$normalizer], [$encoder]);
            $formatted = $serializer->normalize($events);
            return new JsonResponse($formatted);
        } else {
            return new Response("no data");
        }
    }

    public function ImagesUserAction(Request $request)
    {
        $publicResourcesFolderPath = $this->get('kernel')->getRootDir() . '/../web/uploads/';
        $image = $request->query->get("img");

        // This should return the file located in /mySymfonyProject/web/public-resources/TextFile.txt
        // to being viewed in the Browser
        return new BinaryFileResponse($publicResourcesFolderPath . $image);
    }

    public function ImagesAction(Request $request)
    {
        $publicResourcesFolderPath = $this->get('kernel')->getRootDir() . '/../web/affiches/';
        $image = urldecode($request->query->get("img"));

        // This should return the file located in /mySymfonyProject/web/public-resources/TextFile.txt
        // to being viewed in the Browser
        return new BinaryFileResponse($publicResourcesFolderPath . $image);
    }

    public function EventByIdAction(Request $request)
    {
        $id = $request->query->get("id");
        $em = $this->getDoctrine()->getEntityManager();
        $events = $em->getRepository('UtilisateurBundle:Evenement')->find($id);
        $dispo = $events->getNombre() - count($em->getRepository('UtilisateurBundle:Reservation')->findBy(["idEvenement" => $events, "etat" => "Confirmé"]));
        $events->setDisponible($dispo);

        if ($events) {
            $encoder = new JsonEncoder();
            $normalizer = new ObjectNormalizer();
            $normalizer->setCircularReferenceHandler(function ($object) {
                return $object->getId();
            });
            $serializer = new Serializer([$normalizer], [$encoder]);
            $formatted = $serializer->normalize($events);
            return new JsonResponse($formatted);
        } else {
            return new Response("no data");
        }

    }

    public function EditUserAction(Request $request)
    {
        $id = $request->get("id");
        $username = $request->get("username");
        $password = $request->get("password");
        $email = $request->get("email");
        $niveau = $request->get("niveau");
        if ($request->files->get("photo") != null) {
            $file = $request->files->get("photo");
            $fileName = $file->getClientOriginalName();

            // moves the file to the directory where brochures are stored
            $file->move(
                $this->getParameter('user_photo'),
                $fileName
            );
            $file = $request->files->get("photo");
        }

        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository("UtilisateurBundle:User")->find($id);
        $user->setUsername($username);
        $user->setPlainPassword($password);
        $user->setEmail(urldecode($email));
        $user->setNiveau($niveau);
        $user->setEnabled(true);


        try {

            $em = $this->getDoctrine()->getManager();
            $em->persist($user);
            $em->flush();
            return new Response("success");

        } catch (Exception $ex) {
            return new Response("fail");
        }

    }

    public function addEventAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $id_org = $request->get("id_org");
        $organisateur = new User();
        $organisateur = $em->getRepository("UtilisateurBundle:User")->find($id_org);
        $nom = $request->get("nom");
        $type = $request->get("type");
        $res = $request->get("res");
        $duree = $request->get("duree");
        $date = new \DateTime(urldecode($request->get("date")));
        $lieu = $request->get("lieu");
        $quota = $request->get("quota");
        $description = $request->get("description");
        $etat = $request->get("etat");


        if ($request->files->get("photo") != null) {
            $file = $request->files->get("photo");
            $fileName = $file->getClientOriginalName();

            // moves the file to the directory where brochures are stored
            $file->move(
                $this->getParameter('event_photo'),
                $fileName
            );
            $file = $request->files->get("photo");
        }

        $event = new Evenement();
        $event->setNom(urldecode($nom));
        $event->setType(urldecode($type));
        $event->setTypeReservation(urldecode($res));
        $event->setDateEvent($date);
        $event->setDuree(urldecode($duree));
        $event->setLieu(urldecode($lieu));
        $event->setNombre($quota);
        $event->setDescription(urldecode($description));
        $event->setAffiche(urldecode($fileName));
        $event->setEtat(urldecode($etat));
        $event->setIdOrganisateur($organisateur);
        if ($request->get("prix") != null) {
            $prix = $request->get("prix");
            $event->setPrix($prix);
        }

        try {
            $em->persist($event);
            $em->flush();
            return new Response("success");

        } catch (Exception $ex) {
            return new Response("fail");
        }
    }

    public function myEventsAction(Request $request)
    {
        $em = $this->getDoctrine()->getEntityManager();
        $id = $request->get("id");
        $user = new User();
        $user = $em->getRepository('UtilisateurBundle:User')->find($id);
        $events = $em->getRepository('UtilisateurBundle:Evenement')->findBy(["idOrganisateur" => $user]);
        foreach ($events as $event) {
            $dispo = $event->getNombre() - count($em->getRepository('UtilisateurBundle:Reservation')->findBy(["idEvenement" => $event, "etat" => "Confirmé"]));
            $event->setDisponible($dispo);
        }

        if ($events) {
            $encoder = new JsonEncoder();
            $normalizer = new ObjectNormalizer();
            $normalizer->setCircularReferenceHandler(function ($object) {
                return $object->getId();
            });
            $serializer = new Serializer([$normalizer], [$encoder]);
            $formatted = $serializer->normalize($events);
            return new JsonResponse($formatted);
        } else {
            return new Response("no data");
        }
    }

    public function editEventAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $id = $request->get("id");
        $id_org = $request->get("id_org");
        $organisateur = new User();
        $organisateur = $em->getRepository("UtilisateurBundle:User")->find($id_org);
        $nom = $request->get("nom");
        $type = $request->get("type");
        $res = $request->get("res");
        $duree = $request->get("duree");
        $date = new \DateTime(urldecode($request->get("date")));
        $lieu = $request->get("lieu");
        $quota = $request->get("quota");
        $description = $request->get("description");
        $etat = $request->get("etat");

        $event = $em->getRepository("UtilisateurBundle:Evenement")->find($id);

        if ($request->files->get("photo") != null) {
            $file = $request->files->get("photo");
            $fileName = $file->getClientOriginalName();

            // moves the file to the directory where brochures are stored
            $file->move(
                $this->getParameter('event_photo'),
                $fileName
            );
            $event->setAffiche(urldecode($fileName));
            $file = $request->files->get("photo");
        }


        $event->setNom(urldecode($nom));
        $event->setType(urldecode($type));
        $event->setTypeReservation(urldecode($res));
        $event->setDateEvent($date);
        $event->setDuree(urldecode($duree));
        $event->setLieu(urldecode($lieu));
        $event->setNombre($quota);
        $event->setDescription(urldecode($description));
        $event->setEtat(urldecode($etat));
        $event->setIdOrganisateur($organisateur);
        if ($request->get("prix") != null) {
            $prix = $request->get("prix");
            $event->setPrix($prix);
        }

        try {
            $em->persist($event);
            $em->flush();
            return new Response("success");

        } catch (Exception $ex) {
            return new Response("fail");
        }
    }

    public function deleteEventAction(Request $request)
    {
        $id = $request->get("id");
        $em = $this->getDoctrine()->getManager();
        $event = $em->getRepository("UtilisateurBundle:Evenement")->find($id);

        try {
            $em->remove($event);
            $em->flush();
            return new Response("success");

        } catch (Exception $ex) {
            return new Response("fail");
        }
    }

    public function myResEventsAction(Request $request)
    {
        $em = $this->getDoctrine()->getEntityManager();
        $id = $request->get("id");
        $user = new User();
        $user = $em->getRepository('UtilisateurBundle:User')->find($id);
        $reservations = $em->getRepository('UtilisateurBundle:Reservation')->findBy(["idParticipant" => $user, "etat" => "Confirmé"]);
        $events = new ArrayCollection();
        foreach ($reservations as $reservation) {
            $event = $reservation->getIdEvenement();
            $events->add($event);
        }
        foreach ($events as $event) {
            $dispo = $event->getNombre() - count($em->getRepository('UtilisateurBundle:Reservation')->findBy(["idEvenement" => $event, "etat" => "Confirmé"]));
            $event->setDisponible($dispo);
        }

        if ($events) {
            $encoder = new JsonEncoder();
            $normalizer = new ObjectNormalizer();
            $normalizer->setCircularReferenceHandler(function ($object) {
                return $object->getId();
            });
            $serializer = new Serializer([$normalizer], [$encoder]);
            $formatted = $serializer->normalize($events);
            return new JsonResponse($formatted);
        } else {
            return new Response("no data");
        }
    }

    public function annulerReservationAction(Request $request)
    {
        $em = $this->getDoctrine()->getEntityManager();
        $id = $request->get("id");
        $id_ev = $request->get("id_ev");
        $user = new User();
        $reservation = new Reservation();
        $user = $em->getRepository('UtilisateurBundle:User')->find($id);
        $event = $em->getRepository('UtilisateurBundle:Evenement')->find($id_ev);
        $reservation = $em->getRepository('UtilisateurBundle:Reservation')->findOneBy(['idParticipant' => $user, 'idEvenement' => $event]);



        try {
            $reservation->setEtat("Annulé");
            $em->persist($reservation);
            $em->flush();
            return new Response("success");

        } catch (Exception $ex) {
            return new Response("fail");
        }



    }

    public function ReservationAction(Request $request){
        $reservation = new Reservation();
        $em = $this->getDoctrine()->getEntityManager();
        $id = $request->get("id");
        $id_ev = $request->get("id_ev");
        $user = new User();
        $user = $em->getRepository('UtilisateurBundle:User')->find($id);
        $event = $em->getRepository('UtilisateurBundle:Evenement')->find($id_ev);
        $numTicket = count($em->getRepository('UtilisateurBundle:Reservation')->findBy(["idEvenement" => $event, "etat" => "Confirmé"]));
        $reservation->setIdParticipant($user);
        $reservation->setIdEvenement($event);
        $reservation->setTypeReservation($event->getTypeReservation());
        if ($event->getTypeReservation() == "Payante") {
            $reservation->setTarif($event->getPrix());
        }
        if ($numTicket) {
            $reservation->setNumeroTicket($numTicket + 1);
        } else {
            $reservation->setNumeroTicket(1);
        }
        try{
            $reservation->setEtat("Confirmé");
            $event->addReservation($reservation);
            $em->persist($reservation);
            $em->flush();
            return new Response("Success");
        } catch (Exception $ex) {
          return new Response("fail");
         }



    }

}