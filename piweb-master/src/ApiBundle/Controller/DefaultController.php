<?php

namespace ApiBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\Encoder\XmlEncoder;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use UtilisateurBundle\Entity\AdressesCov;
use UtilisateurBundle\Entity\Covoiturage;
use UtilisateurBundle\Entity\CovSignale;
use UtilisateurBundle\Entity\User;
use UtilisateurBundle\Form\CovoiturageType;
use Symfony\Component\HttpFoundation\Request;

class DefaultController extends Controller
{
    public function getlistAction(Request $request){
        $em=$this->getDoctrine()->getManager();
        $covoiturages=$em->getRepository("UtilisateurBundle:Covoiturage")->findAll();
        $encoders = array(new XmlEncoder(), new JsonEncoder());
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $normalizers = array($normalizer);
        $serializer = new Serializer($normalizers, $encoders);
        $data = $serializer->normalize($covoiturages);
        return new JsonResponse($data);

    }
    public function AjoutMobileAction(Request $request){
        $em=$this->getDoctrine()->getManager();
        $covoiturage = new Covoiturage();
        $covoiturage->setDepart($request->get("depart"));
        $covoiturage->setDestination($request->get("destination"));
        $time = strtotime('10/16/2003');

        $date = date('Y-m-d',$time);
        $covoiturage->setDate($date);
        $covoiturage->setType($request->get("type"));
        $em->persist($covoiturage);
        $em->flush();
        return new JsonResponse();
    }
    public function getAdrByNameAction(Request $request){
        $em=$this->getDoctrine()->getManager();
        $encoders = array(new XmlEncoder(), new JsonEncoder());
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $normalizers = array($normalizer);
        $serializer = new Serializer($normalizers, $encoders);
        $adr=$em->getRepository(AdressesCov::class)->findByName($request->get("nom"));
        $data = $serializer->normalize($adr);
        return new JsonResponse($data);
    }
    public function ChercherAction(Request $request){
        $em=$this->getDoctrine()->getManager();
        $encoders = array(new XmlEncoder(), new JsonEncoder());
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $normalizers = array($normalizer);
        $serializer = new Serializer($normalizers, $encoders);
                $covoiturages = $em->getRepository("UtilisateurBundle:Covoiturage")->findCustomMob($request->get('input'));
                $data = $serializer->normalize($covoiturages);
                return new JsonResponse($data);

    }
}
